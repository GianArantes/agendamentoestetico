package br.com.agendamentoestetico.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import br.com.agendamentoestetico.models.AgendaTrabalho;
import br.com.agendamentoestetico.models.enums.DiaDaSemana;

class AgendaTrabalhoTest {
    @Test
    void deveCriarAgendaTrabalhoComDadosCorretos() {
        AgendaTrabalho agenda = new AgendaTrabalho();
        agenda.setDiaDaSemana(DiaDaSemana.SEGUNDA);
        agenda.setAgendaInicio(LocalTime.of(8, 0));
        agenda.setAgendaFim(LocalTime.of(18, 0));
        agenda.setAlmocoInicio(LocalTime.of(12, 0));
        agenda.setAlmocoFim(LocalTime.of(13, 0));
        assertEquals(DiaDaSemana.SEGUNDA, agenda.getDiaDaSemana());
        assertEquals(LocalTime.of(8, 0), agenda.getAgendaInicio());
        assertEquals(LocalTime.of(18, 0), agenda.getAgendaFim());
        assertEquals(LocalTime.of(12, 0), agenda.getAlmocoInicio());
        assertEquals(LocalTime.of(13, 0), agenda.getAlmocoFim());
    }
}
