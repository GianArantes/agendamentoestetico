package br.com.agendamentoestetico.dtos;

import java.time.LocalDateTime;

public record AgendamentoDTO(
    long clienteId,
    long funcionarioId,
    long procedimentoId,
    LocalDateTime inicio
) {

}
