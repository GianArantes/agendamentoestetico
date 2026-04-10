package br.com.agendamentoestetico.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.agendamentoestetico.model.AgendaTrabalho;
import br.com.agendamentoestetico.model.Funcionario;
import br.com.agendamentoestetico.model.enums.DiaDaSemana;
import br.com.agendamentoestetico.model.enums.Status;

@SpringBootTest
@ActiveProfiles("test")
class AgendaTrabalhoRepositoryTest {
    @Autowired
    private AgendaTrabalhoRepository agendaTrabalhoRepository;
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    @Test
    void deveBuscarAgendaPorFuncionarioEDia() {
        // Prepara dados de teste
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Ana Silva");
        funcionario.setCargo("Esteticista");
        funcionario.setStatus(Status.ATIVO);
        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
        
        AgendaTrabalho agenda = new AgendaTrabalho();
        agenda.setFuncionario(funcionarioSalvo);
        agenda.setDiaDaSemana(DiaDaSemana.SEGUNDA);
        agenda.setAgendaInicio(LocalTime.of(8, 0));
        agenda.setAgendaFim(LocalTime.of(18, 0));
        agenda.setAlmocoInicio(LocalTime.of(12, 0));
        agenda.setAlmocoFim(LocalTime.of(13, 0));
        agendaTrabalhoRepository.save(agenda);
        
        // Testa
        Optional<AgendaTrabalho> resultado = agendaTrabalhoRepository.findByFuncionarioIdAndDiaDaSemana(
            funcionarioSalvo.getId(), DiaDaSemana.SEGUNDA);
        assertTrue(resultado.isPresent());
        assertEquals(DiaDaSemana.SEGUNDA, resultado.get().getDiaDaSemana());
    }
}
