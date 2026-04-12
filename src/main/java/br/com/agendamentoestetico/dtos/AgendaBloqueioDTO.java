package br.com.agendamentoestetico.dtos;

import java.time.LocalDateTime;

public record AgendaBloqueioDTO(

        LocalDateTime inicioBloqueio,
        LocalDateTime fimBloqueio,
        String motivo

) {

}
