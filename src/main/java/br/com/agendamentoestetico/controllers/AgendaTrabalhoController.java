package br.com.agendamentoestetico.controllers;

import java.util.List;

import br.com.agendamentoestetico.dtos.AgendaTrabalhoDTOResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.agendamentoestetico.dtos.AgendaTrabalhoDTO;
import br.com.agendamentoestetico.models.AgendaTrabalho;
import br.com.agendamentoestetico.repositories.AgendaTrabalhoRepository;
import br.com.agendamentoestetico.repositories.FuncionarioRepository;
import br.com.agendamentoestetico.services.AgendaTrabalhoService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/agenda-trabalho")
@AllArgsConstructor
public class AgendaTrabalhoController {

    FuncionarioRepository funcionarioRepository;
    AgendaTrabalhoRepository agendaTrabalhoRepository;
    AgendaTrabalhoService agendaTrabalhoService;

    @PostMapping
    public ResponseEntity<AgendaTrabalhoDTOResponse> criar(@RequestBody AgendaTrabalhoDTO dto) {
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
        return ResponseEntity.status(201).body(agendaTrabalho.toDTO());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<List<AgendaTrabalhoDTOResponse>> listar(@PathVariable Long id) {
        var agendaTrabalhoLista = agendaTrabalhoRepository.findByFuncionarioId(id);


        return ResponseEntity.ok(agendaTrabalhoLista.stream().map(AgendaTrabalho::toDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaTrabalhoDTOResponse> get(@PathVariable Long id) {
        var at = agendaTrabalhoRepository.findById(id).orElseThrow(()-> new RuntimeException("Agenda Trabalho não encontrada"));
        return ResponseEntity.ok(at.toDTO());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        agendaTrabalhoRepository.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaTrabalhoDTOResponse> atualizar(@PathVariable Long id, @RequestBody AgendaTrabalhoDTO dto) {

        AgendaTrabalho agendaTrabalho = agendaTrabalhoRepository.findById(id).orElseThrow(()-> new RuntimeException("Agenda Trabalho não encontrada"));
        agendaTrabalho.setDiaDaSemana(dto.diaDaSemana());
        agendaTrabalho.setAgendaInicio(dto.agendaInicio());
        agendaTrabalho.setAgendaFim(dto.agendaFim());
        agendaTrabalho.setAlmocoInicio(dto.almocoInicio());
        agendaTrabalho.setAlmocoFim(dto.almocoFim());

        if (!agendaTrabalhoService.validaAgendaTrabalho(agendaTrabalho))
            throw new RuntimeException("Hora de início e fim do período ou do almoço inválida");

        agendaTrabalho.setFuncionario(funcionarioRepository.findById(dto.funcionarioId()).orElse(null));

        agendaTrabalhoRepository.save(agendaTrabalho);
        return ResponseEntity.status(200).body(agendaTrabalho.toDTO());
    }

}
