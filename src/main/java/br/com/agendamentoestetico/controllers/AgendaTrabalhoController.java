package br.com.agendamentoestetico.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.agendamentoestetico.dtos.AgendaTrabalhoDTO;
import br.com.agendamentoestetico.models.AgendaTrabalho;
import br.com.agendamentoestetico.repositories.AgendaTrabalhoRepository;
import br.com.agendamentoestetico.repositories.FuncionarioRepository;
import br.com.agendamentoestetico.services.AgendaTrabalhoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/agenda-trabalho")
@AllArgsConstructor
public class AgendaTrabalhoController {

    FuncionarioRepository funcionarioRepository;
    AgendaTrabalhoRepository agendaTrabalhoRepository;
    AgendaTrabalhoService agendaTrabalhoService;

    @PostMapping
    public ResponseEntity<AgendaTrabalho> criar(AgendaTrabalhoDTO dto) {
        AgendaTrabalho agendaTrabalho = new AgendaTrabalho();
        agendaTrabalho.setDiaDaSemana(dto.diaDaSemana());
        agendaTrabalho.setAgendaInicio(dto.agendaInicio());
        agendaTrabalho.setAgendaFim(dto.agendaFim());
        agendaTrabalho.setAlmocoInicio(dto.almocoInicio());
        agendaTrabalho.setAlmocoFim(dto.almocoFim());

        if (!agendaTrabalhoService.validaAgendaTrabalho(agendaTrabalho))
            throw new RuntimeException("Hora de início e fim do período ou do almoço inválida");

        agendaTrabalho.setFuncionario(funcionarioRepository.findById(dto.funcionarioId()).orElse(null));

        agendaTrabalhoRepository.save(agendaTrabalho);
        return ResponseEntity.status(201).body(agendaTrabalho);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AgendaTrabalho>> listar() {
        return ResponseEntity.ok(agendaTrabalhoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaTrabalho> get(@PathVariable Long id) {
        return ResponseEntity.ok(agendaTrabalhoRepository.findById(id).orElse(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        agendaTrabalhoRepository.deleteById(id);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaTrabalho> atualizar(@PathVariable Long id, @RequestBody AgendaTrabalhoDTO dto) {

        AgendaTrabalho agendaTrabalho = agendaTrabalhoRepository.findById(id).orElse(null);
        agendaTrabalho.setDiaDaSemana(dto.diaDaSemana());
        agendaTrabalho.setAgendaInicio(dto.agendaInicio());
        agendaTrabalho.setAgendaFim(dto.agendaFim());
        agendaTrabalho.setAlmocoInicio(dto.almocoInicio());
        agendaTrabalho.setAlmocoFim(dto.almocoFim());

        if (!agendaTrabalhoService.validaAgendaTrabalho(agendaTrabalho))
            throw new RuntimeException("Hora de início e fim do período ou do almoço inválida");

        agendaTrabalho.setFuncionario(funcionarioRepository.findById(dto.funcionarioId()).orElse(null));

        agendaTrabalhoRepository.save(agendaTrabalho);
        return ResponseEntity.status(200).body(agendaTrabalho);
    }

}
