package br.com.agendamentoestetico.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class ClienteTest {
    @Test
    void deveCriarClienteComDadosCorretos() {
        Cliente cliente = new Cliente();
        cliente.setNome("Maria");
        cliente.setEmail("maria@email.com");
        cliente.setTelefone("11999999999");
        cliente.setDataNascimento(LocalDate.of(1990, 1, 1));
        assertEquals("Maria", cliente.getNome());
        assertEquals("maria@email.com", cliente.getEmail());
        assertEquals("11999999999", cliente.getTelefone());
        assertEquals(LocalDate.of(1990, 1, 1), cliente.getDataNascimento());
    }

    @Test
    void toStringDeveRetornarDescricaoCompleta() {
        Cliente cliente = new Cliente();
        cliente.setNome("João");
        cliente.setEmail("joao@email.com");
        cliente.setTelefone("11988888888");
        cliente.setDataNascimento(LocalDate.of(1985, 5, 20));
        String descricao = cliente.toString();
        assertTrue(descricao.contains("João"));
        assertTrue(descricao.contains("1985-05-20"));
    }
}
