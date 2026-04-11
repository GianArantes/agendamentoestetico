package br.com.agendamentoestetico.dtos;

import jakarta.validation.constraints.NotBlank;

public record RecuperaSenhaDTO(
        @NotBlank(message = "Digite a nova senha.") String novaSenha,
        @NotBlank(message = "Confirme a nova senha.") String confirmaNovaSenha) {

}
