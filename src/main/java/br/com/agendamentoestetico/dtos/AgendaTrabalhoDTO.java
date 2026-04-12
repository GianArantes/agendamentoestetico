package br.com.agendamentoestetico.dtos;

import java.time.LocalTime;
import br.com.agendamentoestetico.models.enums.DiaDaSemana;

public record AgendaTrabalhoDTO(

        DiaDaSemana diaDaSemana,
        LocalTime agendaInicio, // Ex: 08:00
        LocalTime agendaFim, // Ex: 18:00
        LocalTime almocoInicio, // Ex: 12:00
        LocalTime almocoFim, // Ex: 13:00
        Long funcionarioId // ID do funcionário

) {

}
