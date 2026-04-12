package br.com.agendamentoestetico.dtos;

import java.time.LocalDate;

public record ClienteDTO(
        String nome,
        String celular,
        String email,
        LocalDate dataNascimento
) {
}
