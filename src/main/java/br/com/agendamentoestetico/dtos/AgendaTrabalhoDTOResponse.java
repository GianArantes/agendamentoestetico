package br.com.agendamentoestetico.dtos;

import br.com.agendamentoestetico.models.enums.DiaDaSemana;


import java.time.LocalTime;

public record AgendaTrabalhoDTOResponse(

        Long id,
        DiaDaSemana diaDaSemana,

        LocalTime agendaInicio, // Ex: 08:00

        LocalTime agendaFim, // Ex: 18:00

        LocalTime almocoInicio, // Ex: 12:00

        LocalTime almocoFim, // Ex: 13:00

        Long funcionarioId // ID do funcionário

) {

}
