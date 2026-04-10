package br.com.agendamentoestetico.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.Duration;

import org.junit.jupiter.api.Test;

class ProcedimentoTest {
    @Test
    void deveCriarProcedimentoComDadosCorretos() {
        Procedimento procedimento = new Procedimento();
        procedimento.setNome("Limpeza de Pele");
        procedimento.setDescricao("Limpeza profunda da pele");
        procedimento.setPreco(new BigDecimal("120.00"));
        procedimento.setDuracao(Duration.ofMinutes(60));
        assertEquals("Limpeza de Pele", procedimento.getNome());
        assertEquals("Limpeza profunda da pele", procedimento.getDescricao());
        assertEquals(new BigDecimal("120.00"), procedimento.getPreco());
        assertEquals(Duration.ofMinutes(60), procedimento.getDuracao());
    }
}
