package br.com.agendamentoestetico.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.agendamentoestetico.models.Procedimento;

@SpringBootTest
@ActiveProfiles("test")
class ProcedimentoRepositoryTest {
    @Autowired
    private ProcedimentoRepository procedimentoRepository;
    
    @Test
    void deveSalvarProcedimentoCorretamente() {
        Procedimento procedimento = new Procedimento();
        procedimento.setNome("Micropigmentação");
        procedimento.setDescricao("Procedimento de micropigmentação de sobrancelha");
        procedimento.setPreco(new BigDecimal("250.00"));
        procedimento.setDuracao(Duration.ofMinutes(120));
        
        Procedimento salvo = procedimentoRepository.save(procedimento);
        assertNotNull(salvo.getId());
        assertEquals("Micropigmentação", salvo.getNome());
        assertEquals(new BigDecimal("250.00"), salvo.getPreco());
    }
    
    @Test
    void deveBuscarProcedimentoPorId() {
        Procedimento procedimento = new Procedimento();
        procedimento.setNome("Botox");
        procedimento.setPreco(new BigDecimal("400.00"));
        procedimento.setDuracao(Duration.ofMinutes(30));
        Procedimento salvo = procedimentoRepository.save(procedimento);
        
        var encontrado = procedimentoRepository.findById(salvo.getId());
        assertTrue(encontrado.isPresent());
    }
}
