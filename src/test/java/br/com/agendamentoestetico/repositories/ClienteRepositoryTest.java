package br.com.agendamentoestetico.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.agendamentoestetico.model.Cliente;
import br.com.agendamentoestetico.model.enums.Status;

@SpringBootTest
@ActiveProfiles("test")
class ClienteRepositoryTest {
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Test
    void deveSalvarClienteCorretamente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Pedro Santos");
        cliente.setEmail("pedro@email.com");
        cliente.setTelefone("11999999999");
        cliente.setStatus(Status.ATIVO);
        
        Cliente salvo = clienteRepository.save(cliente);
        assertNotNull(salvo.getId());
        assertEquals("Pedro Santos", salvo.getNome());
    }
    
    @Test
    void deveBuscarClientePorId() {
        Cliente cliente = new Cliente();
        cliente.setNome("Ana Costa");
        cliente.setStatus(Status.ATIVO);
        Cliente salvo = clienteRepository.save(cliente);
        
        var encontrado = clienteRepository.findById(salvo.getId());
        assertTrue(encontrado.isPresent());
    }
}
