package br.com.agendamentoestetico.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

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
        AgendaTrabalho agenda = agendaTrabalhoRepository.findByFuncionarioIdAndDiaDaSemana(funcionarioId, DiaDaSemana.de(data.getDayOfWeek()))
                .orElseThrow(() -> new RuntimeException("Funcionário não trabalha neste dia."));

        // 2. Busca o que já está ocupado no banco para este dia específico
        // Criamos o intervalo de tempo do dia (00:00 até 23:59)
        LocalDateTime inicioDia = data.atStartOfDay();
        LocalDateTime fimDia = data.atTime(LocalTime.MAX);
        List<Agendamento> ocupados = agendamentoRepository.findAllByFuncionarioIdAndInicioProcedimentoBetween(funcionarioId, inicioDia, fimDia);
        
        List<LocalTime> horariosLivres = new ArrayList<>();
        Duration duracaoProcedimento = procedimento.getDuracao();

        // 3. O 'ponteiro' deve começar no INÍCIO do expediente, não no fim
        LocalTime horarioCandidato = agenda.getAgendaInicio(); 
        LocalTime limiteFim = agenda.getAgendaFim();
        
        // Loop: Enquanto o horário de início + duração não ultrapassar o fim do expediente
        while (!horarioCandidato.plus(duracaoProcedimento).isAfter(limiteFim)) {
            
            LocalTime fimCandidato = horarioCandidato.plus(duracaoProcedimento);
            
            if (isHorarioValido(horarioCandidato, fimCandidato, agenda, ocupados)) {
                horariosLivres.add(horarioCandidato);
            }

            // Pula de 15 em 15 minutos para oferecer a próxima opção
            horarioCandidato = horarioCandidato.plusMinutes(15);
        }

        return horariosLivres;
    }

    private boolean isHorarioValido(LocalTime inicio, LocalTime fim, AgendaTrabalho agenda, List<Agendamento> ocupados) {
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