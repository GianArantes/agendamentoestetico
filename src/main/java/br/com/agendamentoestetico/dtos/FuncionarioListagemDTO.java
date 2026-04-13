package br.com.agendamentoestetico.dtos;

import br.com.agendamentoestetico.models.Funcionario;
import br.com.agendamentoestetico.models.enums.UserRole;

public record FuncionarioListagemDTO(
    Long id, 
    String nome, 
    String email, 
    String celular, 
    UserRole role
) {
    public FuncionarioListagemDTO(Funcionario f) {
        this(f.getId(), f.getNome(), f.getEmail(), f.getCelular(), f.getRole());
    }
}