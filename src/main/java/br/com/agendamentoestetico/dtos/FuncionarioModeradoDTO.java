package br.com.agendamentoestetico.dtos;

import br.com.agendamentoestetico.models.enums.*;
import jakarta.validation.constraints.NotBlank;

public record FuncionarioModeradoDTO(
    @NotBlank(message = "O nome é obrigatório.")
    String nome,
    @NotBlank(message = "O email é obrigatório.")
    String email,
    @NotBlank(message = "O celular é obrigatório.")
    String celular,
    String novaSenha,
    UserRole role,
    Status status

    
) {

}