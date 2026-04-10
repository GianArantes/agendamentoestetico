package br.com.agendamentoestetico.model.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StatusTest {
    @Test
    void deveConterTodosOsStatus() {
        Status[] values = Status.values();
        assertTrue(values.length >= 3);
        assertTrue(java.util.Arrays.asList(values).contains(Status.ATIVO));
        assertTrue(java.util.Arrays.asList(values).contains(Status.INATIVO));
        assertTrue(java.util.Arrays.asList(values).contains(Status.BLOLOQUEADO));
    }
}
