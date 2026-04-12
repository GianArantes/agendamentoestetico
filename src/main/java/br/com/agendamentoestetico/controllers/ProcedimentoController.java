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

import br.com.agendamentoestetico.dtos.ProcedimentoDTO;
import br.com.agendamentoestetico.models.Procedimento;
import br.com.agendamentoestetico.repositories.ProcedimentoRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/procedimentos")
@AllArgsConstructor
public class ProcedimentoController {

    private final ProcedimentoRepository procedimentoRepository;

    @PostMapping
    public ResponseEntity<Procedimento> cadastrar(ProcedimentoDTO dto) {
        Procedimento procedimento = new Procedimento();
        procedimento.setDescricao(dto.descricao());
        procedimento.setDuracao(dto.duracao());
        procedimento.setPreco(dto.preco());
        procedimento.setNome(dto.nome());
        procedimentoRepository.save(procedimento);
        return ResponseEntity.status(201).body(procedimento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Procedimento> atualizar(@PathVariable Long id, @RequestBody ProcedimentoDTO dto) {
        Procedimento procedimento = procedimentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Procedimento não encontrado"));
        procedimento.setDescricao(dto.descricao());
        procedimento.setDuracao(dto.duracao());
        procedimento.setPreco(dto.preco());
        procedimento.setNome(dto.nome());
        procedimentoRepository.save(procedimento);
        return ResponseEntity.status(200).body(procedimento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Procedimento> buscar(@PathVariable Long id) {
        return ResponseEntity.status(200).body(procedimentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Procedimento não encontrado")));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Procedimento>> listar() {
        return ResponseEntity.status(200).body(procedimentoRepository.findAll());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        procedimentoRepository.deleteById(id);
        return ResponseEntity.status(204).build();
    }

}
