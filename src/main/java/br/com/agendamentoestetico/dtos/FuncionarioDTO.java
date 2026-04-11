package br.com.agendamentoestetico.dtos;

import jakarta.validation.constraints.NotBlank;

public record FuncionarioDTO(
    @NotBlank(message = "O nome é obrigatório.")
    String nome,
    @NotBlank(message = "O email é obrigatório.")
    String email,
    @NotBlank(message = "O celular é obrigatório.")
    String celular,
    @NotBlank(message = "O senha é obrigatória.")
    String senha
    
) {

}
