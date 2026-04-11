package br.com.agendamentoestetico.dtos;

import jakarta.validation.constraints.NotBlank;

public record AtualizaSenhaDTO(

        @NotBlank(message = "Digite a senha atual.") String senhaAntiga,
        @NotBlank(message = "Digite a nova senha.") String novaSenha,
        @NotBlank(message = "Confirme a nova senha.") String confirmaNovaSenha

) {

}
