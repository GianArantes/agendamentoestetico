package br.com.agendamentoestetico.dtos;

import jakarta.validation.constraints.NotBlank;

public record AuthDTO(
    @NotBlank(message = "O email é obrigatório.")
    String email, 
    @NotBlank(message = "O senha é obrigatória.")
    String senha) {

}