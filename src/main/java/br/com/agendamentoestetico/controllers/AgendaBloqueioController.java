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

import br.com.agendamentoestetico.dtos.AgendaBloqueioDTO;
import br.com.agendamentoestetico.models.AgendaBloqueio;
import br.com.agendamentoestetico.repositories.AgendaBloqueioRepository;
import br.com.agendamentoestetico.services.AgendaBloqueioService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/agenda-bloqueio")
@AllArgsConstructor
public class AgendaBloqueioController {

    private final AgendaBloqueioRepository agendaBloqueioRepository;
    private AgendaBloqueioService agendaBloqueioService;

    @PostMapping
    public ResponseEntity<AgendaBloqueio> criar(@RequestBody AgendaBloqueioDTO dto) {
        
        AgendaBloqueio agendaBloqueio = new AgendaBloqueio();
        agendaBloqueio.setMotivo(dto.motivo());
        agendaBloqueio.setInicioBloqueio(dto.inicioBloqueio());
        agendaBloqueio.setFimBloqueio(dto.fimBloqueio());

        if (!agendaBloqueioService.valida(agendaBloqueio))
            throw new RuntimeException("Hora de início e fim do período inválida");

        agendaBloqueioRepository.save(agendaBloqueio);

        return ResponseEntity.status(201).body(agendaBloqueio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaBloqueio> atualizar(@PathVariable long id, @RequestBody AgendaBloqueioDTO dto) {
        AgendaBloqueio agendaBloqueio = agendaBloqueioRepository.findById(id).orElseThrow(() -> new RuntimeException("Agenda bloqueio não encontrado"));
        agendaBloqueio.setMotivo(dto.motivo());
        agendaBloqueio.setInicioBloqueio(dto.inicioBloqueio());
        agendaBloqueio.setFimBloqueio(dto.fimBloqueio());

        if (!agendaBloqueioService.valida(agendaBloqueio))
            throw new RuntimeException("Hora de início e fim do período inválida");

        agendaBloqueioRepository.save(agendaBloqueio);
        return ResponseEntity.ok(agendaBloqueio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaBloqueio> buscar(@PathVariable long id) {
        return ResponseEntity.ok(agendaBloqueioRepository.findById(id).orElseThrow(() -> new RuntimeException("Agenda bloqueio não encontrado")));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AgendaBloqueio>> listar(){
        return ResponseEntity.ok(agendaBloqueioRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable long id) {
        agendaBloqueioRepository.deleteById(id);
        return ResponseEntity.status(204).build();
    }

}
