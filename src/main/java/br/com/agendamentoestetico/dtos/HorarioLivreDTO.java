package br.com.agendamentoestetico.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record HorarioLivreDTO(
        @NotNull LocalDate data,
        @NotNull Long funcionarioId,
        @NotNull Long procedimentoId // Passamos o ID e buscamos o objeto no Service/Controller
) {
}
