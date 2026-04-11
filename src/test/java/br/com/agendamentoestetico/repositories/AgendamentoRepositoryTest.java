package br.com.agendamentoestetico.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.agendamentoestetico.models.Agendamento;
import br.com.agendamentoestetico.models.Cliente;
import br.com.agendamentoestetico.models.Funcionario;
import br.com.agendamentoestetico.models.Procedimento;
import br.com.agendamentoestetico.models.enums.Status;
import br.com.agendamentoestetico.models.enums.UserRole;

@SpringBootTest
@ActiveProfiles("test")
class AgendamentoRepositoryTest {
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    @Autowired
    private ProcedimentoRepository procedimentoRepository;
    
    @Test
    void deveBuscarAgendamentosPorFuncionarioEData() {
        // Prepara dados de teste
        Cliente cliente = new Cliente();
        cliente.setNome("João Silva");
        cliente.setEmail("joao@email.com");
        cliente.setStatus(Status.ATIVO);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Maria Esteta");
        funcionario.setRole(UserRole.ROLE_FUNCIONARIO);
        funcionario.setStatus(Status.ATIVO);
        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
        
        Procedimento procedimento = new Procedimento();
        procedimento.setNome("Limpeza de Pele");
        Procedimento procedimentoSalvo = procedimentoRepository.save(procedimento);
        
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime inicio = agora.withHour(0).withMinute(0);
        LocalDateTime fim = agora.withHour(23).withMinute(59);
        
        Agendamento agendamento1 = new Agendamento();
        agendamento1.setCliente(clienteSalvo);
        agendamento1.setFuncionario(funcionarioSalvo);
        agendamento1.setProcedimento(procedimentoSalvo);
        agendamento1.setInicioProcedimento(agora);
        agendamento1.setFimProcedimento(agora.plusMinutes(30));
        agendamentoRepository.save(agendamento1);
        
        // Testa
        List<Agendamento> resultado = agendamentoRepository.findAllByFuncionarioIdAndInicioProcedimentoBetween(funcionarioSalvo.getId(), inicio, fim);
        assertNotNull(resultado);
        assertTrue(resultado.size() > 0);
    }
}
