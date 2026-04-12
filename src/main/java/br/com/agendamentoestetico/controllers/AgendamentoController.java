package br.com.agendamentoestetico.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.agendamentoestetico.dtos.AgendamentoDTO;
import br.com.agendamentoestetico.dtos.HorarioLivreDTO;
import br.com.agendamentoestetico.dtos.IntervaloOcupadoDTO;
import br.com.agendamentoestetico.models.Agendamento;
import br.com.agendamentoestetico.models.Procedimento;
import br.com.agendamentoestetico.repositories.AgendamentoRepository;
import br.com.agendamentoestetico.repositories.ProcedimentoRepository;
import br.com.agendamentoestetico.services.AgendaService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/agendamento")
@AllArgsConstructor
public class AgendamentoController {

    private final AgendaService agendaService;
    private final AgendamentoRepository agendamentoRepository;
    private final ProcedimentoRepository procedimentoRepository;

    /**
     * Endpoint para o frontend carregar a lista de horários quando o usuário
     * escolhe uma data
     */
    @GetMapping("/horarios-disponiveis")
    public ResponseEntity<List<LocalTime>> listarHorarios(HorarioLivreDTO dto) {

        Procedimento procedimento = procedimentoRepository.findById(dto.procedimentoId())
                .orElseThrow(() -> new RuntimeException("Procedimento não encontrado"));

        List<LocalTime> horarios = agendaService.listarHorariosDisponiveis(
                dto.data(),
                dto.funcionarioId(),
                procedimento);

        return ResponseEntity.ok(horarios);
    }

    /**
     * Endpoint que recebe a escolha do usuário e salva no banco
     */
    @PostMapping
    public ResponseEntity<Agendamento> criar(@RequestBody AgendamentoDTO dto) {

        Procedimento procedimento = procedimentoRepository.findById(dto.procedimentoId())
                .orElseThrow(() -> new RuntimeException("Procedimento não encontrado"));

        // Criando a Model a partir do DTO
        Agendamento agendamento = new Agendamento();
        agendamento.setInicioProcedimento(dto.inicio());

        // Calculando o fim automaticamente com base na duração do procedimento
        agendamento.setFimProcedimento(dto.inicio().plus(procedimento.getDuracao()));

        // Aqui você setaria o Cliente e o Funcionário buscando pelos IDs do request
        // agendamento.setFuncionario(funcionarioRepository.findById(request.funcionarioId()).get());

        Agendamento salvo = agendamentoRepository.save(agendamento);

        return ResponseEntity.status(201).body(salvo);
    }

    @GetMapping("/ocupados")
    public ResponseEntity<List<IntervaloOcupadoDTO>> buscarOcupados(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam Long funcionarioId) {

        return ResponseEntity.ok(agendaService.listarHorariosOcupados(data, funcionarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        agendamentoRepository.deleteById(id);
        return ResponseEntity.status(204).build();
    }


    /**
     * Verifica se o funcionário está logado, se não, considera como um cliente e valida o token (pendente implementar a geração de token e envio via email)
     */
    private boolean precisaValidarCliente() {
        var email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (email == null) {
            return true;
        }
        return false;
    }

}
