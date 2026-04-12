package br.com.agendamentoestetico.dtos;

import java.math.BigDecimal;
import java.time.Duration;

public record ProcedimentoDTO(

        String nome,
        String descricao,
        BigDecimal preco,
        Duration duracao

) {

}
