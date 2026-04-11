package br.com.agendamentoestetico.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.agendamentoestetico.models.Funcionario;
import br.com.agendamentoestetico.models.enums.Status;
import br.com.agendamentoestetico.models.enums.UserRole;

@SpringBootTest
@ActiveProfiles("test")
class FuncionarioRepositoryTest {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    @Test
    void deveSalvarFuncionarioCorretamente() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Carlos Silva");
        funcionario.setRole(UserRole.ROLE_FUNCIONARIO);
        funcionario.setEmail("carlos@email.com");
        funcionario.setSenha("123456");
        funcionario.setStatus(Status.ATIVO);
        
        Funcionario salvo = funcionarioRepository.save(funcionario);
        assertNotNull(salvo.getId());
        assertEquals("Carlos Silva", salvo.getNome());
        assertEquals("ROLE_FUNCIONARIO", salvo.getRole().name());
    }
    
    @Test
    void deveBuscarFuncionarioPorId() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Marina Esteta");
        funcionario.setRole(UserRole.ROLE_GERENTE);
        funcionario.setStatus(Status.ATIVO);
        Funcionario salvo = funcionarioRepository.save(funcionario);
        
        var encontrado = funcionarioRepository.findById(salvo.getId());
        assertTrue(encontrado.isPresent());
    }
}
