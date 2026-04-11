package br.com.agendamentoestetico.model.enums;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;

import org.junit.jupiter.api.Test;

import br.com.agendamentoestetico.models.enums.DiaDaSemana;

class DiaDaSemanaTest {
    @Test
    void deveConverterDayOfWeekParaDiaDaSemana() {
        assertEquals(DiaDaSemana.SEGUNDA, DiaDaSemana.de(DayOfWeek.MONDAY));
        assertEquals(DiaDaSemana.TERCA, DiaDaSemana.de(DayOfWeek.TUESDAY));
        assertEquals(DiaDaSemana.QUARTA, DiaDaSemana.de(DayOfWeek.WEDNESDAY));
        assertEquals(DiaDaSemana.QUINTA, DiaDaSemana.de(DayOfWeek.THURSDAY));
        assertEquals(DiaDaSemana.SEXTA, DiaDaSemana.de(DayOfWeek.FRIDAY));
        assertEquals(DiaDaSemana.SABADO, DiaDaSemana.de(DayOfWeek.SATURDAY));
        assertEquals(DiaDaSemana.DOMINGO, DiaDaSemana.de(DayOfWeek.SUNDAY));
    }
}
