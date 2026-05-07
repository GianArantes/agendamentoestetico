package br.com.agendamentoestetico.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import br.com.agendamentoestetico.dtos.IntervaloOcupadoDTO;
import br.com.agendamentoestetico.models.AgendaTrabalho;
import br.com.agendamentoestetico.models.Agendamento;
import br.com.agendamentoestetico.models.Procedimento;
import br.com.agendamentoestetico.models.enums.DiaDaSemana;
import br.com.agendamentoestetico.repositories.AgendaTrabalhoRepository;
import br.com.agendamentoestetico.repositories.AgendamentoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AgendaService {

    private AgendamentoRepository agendamentoRepository;
    private AgendaTrabalhoRepository agendaTrabalhoRepository;

    public List<LocalTime> listarHorariosDisponiveis(LocalDate data, Long funcionarioId, Procedimento procedimento) {
        // 1. Busca a regra de trabalho (Configuração fixa)
        AgendaTrabalho agenda = agendaTrabalhoRepository
                .findByFuncionarioIdAndDiaDaSemana(funcionarioId, DiaDaSemana.de(data.getDayOfWeek()))
                .orElseThrow(() -> new RuntimeException("Funcionário não trabalha neste dia."));

        // 2. Busca o que já está ocupado no banco para este dia específico
        // Criamos o intervalo de tempo do dia (00:00 até 23:59)
        LocalDateTime inicioDia = data.atStartOfDay();
        LocalDateTime fimDia = data.atTime(LocalTime.MAX);
        List<Agendamento> ocupados = agendamentoRepository
                .findAllByFuncionarioIdAndInicioProcedimentoBetween(funcionarioId, inicioDia, fimDia);

        List<LocalTime> horariosLivres = new ArrayList<>();
        Duration duracaoProcedimento = procedimento.getDuracao();

        // 3. O 'ponteiro' deve começar no INÍCIO do expediente, não no fim
        LocalTime horarioCandidato = agenda.getAgendaInicio();
        LocalTime limiteFim = agenda.getAgendaFim();

        // Loop: Enquanto o horário de início + duração não ultrapassar o fim do
        // expediente
        while (!horarioCandidato.plus(duracaoProcedimento).isAfter(limiteFim)) {

            LocalTime fimCandidato = horarioCandidato.plus(duracaoProcedimento);

            if (isHorarioValido(horarioCandidato, fimCandidato, agenda, ocupados)) {
                horariosLivres.add(horarioCandidato);
            }

            // Pula de 30 em 30 minutos para oferecer a próxima opção
            horarioCandidato = horarioCandidato.plusMinutes(30);
        }

        return horariosLivres;
    }

    public List<IntervaloOcupadoDTO> listarHorariosOcupados(LocalDate data, Long funcionarioId) {
        List<IntervaloOcupadoDTO> ocupados = new ArrayList<>();

        // 1. Busca a regra de trabalho para o dia
        AgendaTrabalho agenda = agendaTrabalhoRepository.findByFuncionarioIdAndDiaDaSemana(
                funcionarioId, DiaDaSemana.de(data.getDayOfWeek()))
                .orElseThrow(() -> new RuntimeException("Funcionário não trabalha neste dia."));

        // 2. Adiciona o Almoço como um horário ocupado
         ocupados.add(new IntervaloOcupadoDTO(
                 agenda.getAlmocoInicio(),
                 agenda.getAlmocoFim(),
                 "Intervalo de Almoço"));

        // 3. Busca agendamentos do banco de dados
        LocalDateTime inicioDia = data.atStartOfDay();
        LocalDateTime fimDia = data.atTime(LocalTime.MAX);
        List<Agendamento> agendamentosNoBanco = agendamentoRepository
                .findAllByFuncionarioIdAndInicioProcedimentoBetween(funcionarioId, inicioDia, fimDia);

        // 4. Converte os agendamentos para o nosso DTO de intervalo
        for (Agendamento app : agendamentosNoBanco) {
            ocupados.add(new IntervaloOcupadoDTO(
                    app.getInicioProcedimento().toLocalTime(),
                    app.getFimProcedimento().toLocalTime(),
                    app.getFuncionario().getNome() + " - " + app.getCliente().getCelular() + " - " + app.getProcedimento().getNome()));
        }

        // 5. Opcional: Adicionar "Fora de Expediente" (antes do início e após o fim)
        if (agenda.getAgendaInicio().isAfter(LocalTime.MIN)) {
            ocupados.add(new IntervaloOcupadoDTO(LocalTime.MIN,agenda.getAgendaInicio(),"Fechado"));
        }
        if (agenda.getAgendaFim().isBefore(LocalTime.MAX)) {
            ocupados.add(new IntervaloOcupadoDTO(agenda.getAgendaFim(), LocalTime.MAX, "Fechado"));
        }

        // Ordena a lista por horário de início para facilitar a vida do frontend
        ocupados.sort((a, b) -> a.inicioBloqueio().compareTo(b.fimBloqueio()));

        return ocupados;
    }

    private boolean isHorarioValido(LocalTime inicio, LocalTime fim, AgendaTrabalho agenda,
            List<Agendamento> ocupados) {
        // Regra 1: Conflita com o intervalo de almoço?
        if (inicio.isBefore(agenda.getAlmocoFim()) && fim.isAfter(agenda.getAlmocoInicio())) {
            return false;
        }

        // Regra 2: Conflita com agendamentos já salvos?
        for (Agendamento app : ocupados) {
            LocalTime appInicio = app.getInicioProcedimento().toLocalTime();
            LocalTime appFim = app.getFimProcedimento().toLocalTime();

            if (inicio.isBefore(appFim) && appInicio.isBefore(fim)) {
                return false;
            }
        }

        return true;
    }
}