package br.com.agendamentoestetico.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.agendamentoestetico.models.Funcionario;
import br.com.agendamentoestetico.models.enums.UserRole;

class FuncionarioTest {
    @Test
    void deveCriarFuncionarioComDadosCorretos() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Carlos");
        funcionario.setEmail("carlos@email.com");
        funcionario.setCelular("11977777777");
        funcionario.setRole(UserRole.ROLE_FUNCIONARIO);
        funcionario.setSenha("123456");
        assertEquals("Carlos", funcionario.getNome());
        assertEquals("carlos@email.com", funcionario.getEmail());
        assertEquals("11977777777", funcionario.getCelular());
        assertEquals("ROLE_FUNCIONARIO", funcionario.getRole().name());
        assertEquals("123456", funcionario.getSenha());
    }

    @Test
    void toStringDeveRetornarDescricaoCompleta() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Ana");
        funcionario.setEmail("ana@email.com");
        funcionario.setCelular("11966666666");
        funcionario.setRole(UserRole.ROLE_FUNCIONARIO);
        String descricao = funcionario.toString();
        assertTrue(descricao.contains("Ana"));
        assertTrue(descricao.contains("ana@email.com"));
    }
}
