package br.com.agendamentoestetico.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FuncionarioTest {
    @Test
    void deveCriarFuncionarioComDadosCorretos() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Carlos");
        funcionario.setEmail("carlos@email.com");
        funcionario.setTelefone("11977777777");
        funcionario.setCargo("Esteticista");
        funcionario.setSenha("123456");
        assertEquals("Carlos", funcionario.getNome());
        assertEquals("carlos@email.com", funcionario.getEmail());
        assertEquals("11977777777", funcionario.getTelefone());
        assertEquals("Esteticista", funcionario.getCargo());
        assertEquals("123456", funcionario.getSenha());
    }

    @Test
    void toStringDeveRetornarDescricaoCompleta() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Ana");
        funcionario.setEmail("ana@email.com");
        funcionario.setTelefone("11966666666");
        funcionario.setCargo("Recepcionista");
        String descricao = funcionario.toString();
        assertTrue(descricao.contains("Ana"));
        assertTrue(descricao.contains("Recepcionista"));
    }
}
