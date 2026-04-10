package br.com.agendamentoestetico.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class AgendaBloqueioTest {
    @Test
    void deveCriarAgendaBloqueioComDadosCorretos() {
        AgendaBloqueio bloqueio = new AgendaBloqueio();
        bloqueio.setInicioBloqueio(LocalDateTime.of(2026, 4, 10, 8, 0));
        bloqueio.setFimBloqueio(LocalDateTime.of(2026, 4, 10, 10, 0));
        bloqueio.setMotivo("Manutenção");
        assertEquals(LocalDateTime.of(2026, 4, 10, 8, 0), bloqueio.getInicioBloqueio());
        assertEquals(LocalDateTime.of(2026, 4, 10, 10, 0), bloqueio.getFimBloqueio());
        assertEquals("Manutenção", bloqueio.getMotivo());
    }
}
