package br.com.agendamentoestetico.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.agendamentoestetico.models.AgendaBloqueio;
import br.com.agendamentoestetico.models.Funcionario;
import br.com.agendamentoestetico.models.enums.Status;

@SpringBootTest
@ActiveProfiles("test")
class AgendaBloqueioRepositoryTest {
    @Autowired
    private AgendaBloqueioRepository agendaBloqueioRepository;
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    @Test
    void deveSalvarAgendaBloqueioCorretamente() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Lucas Esteta");
        funcionario.setStatus(Status.ATIVO);
        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
        
        AgendaBloqueio bloqueio = new AgendaBloqueio();
        bloqueio.setFuncionario(funcionarioSalvo);
        bloqueio.setInicioBloqueio(LocalDateTime.of(2026, 4, 10, 8, 0));
        bloqueio.setFimBloqueio(LocalDateTime.of(2026, 4, 10, 10, 0));
        bloqueio.setMotivo("Manutenção");
        
        AgendaBloqueio salvo = agendaBloqueioRepository.save(bloqueio);
        assertNotNull(salvo.getId());
        assertEquals("Manutenção", salvo.getMotivo());
    }
}
