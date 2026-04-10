package br.com.agendamentoestetico.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class AgendamentoTest {
    @Test
    void deveCriarAgendamentoComDadosCorretos() {
        Cliente cliente = new Cliente();
        Funcionario funcionario = new Funcionario();
        Procedimento procedimento = new Procedimento();
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fim = inicio.plusMinutes(30);

        Agendamento agendamento = new Agendamento();
        agendamento.setCliente(cliente);
        agendamento.setFuncionario(funcionario);
        agendamento.setProcedimento(procedimento);
        agendamento.setInicioProcedimento(inicio);
        agendamento.setFimProcedimento(fim);

        assertEquals(cliente, agendamento.getCliente());
        assertEquals(funcionario, agendamento.getFuncionario());
        assertEquals(procedimento, agendamento.getProcedimento());
        assertEquals(inicio, agendamento.getInicioProcedimento());
        assertEquals(fim, agendamento.getFimProcedimento());
    }
}
