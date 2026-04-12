package br.com.agendamentoestetico.dtos;

import java.math.BigDecimal;

public record ProcedimentoDTO(

        String nome,
        String descricao,
        BigDecimal preco,
        Integer duracao

) {

}
