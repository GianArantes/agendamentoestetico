package br.com.agendamentoestetico.dtos;

import java.time.LocalTime;

public record IntervaloOcupadoDTO(

        LocalTime inicioBloqueio,
        LocalTime fimBloqueio,
        String motivo

) {

}
