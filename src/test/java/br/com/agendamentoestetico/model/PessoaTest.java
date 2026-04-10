package br.com.agendamentoestetico.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.agendamentoestetico.model.enums.Status;

class PessoaTestImpl extends Pessoa {}

class PessoaTest {
    @Test
    void deveCriarPessoaComDadosCorretos() {
        Pessoa pessoa = new PessoaTestImpl();
        pessoa.setNome("Teste");
        pessoa.setEmail("teste@email.com");
        pessoa.setTelefone("11955555555");
        pessoa.setStatus(Status.ATIVO);
        assertEquals("Teste", pessoa.getNome());
        assertEquals("teste@email.com", pessoa.getEmail());
        assertEquals("11955555555", pessoa.getTelefone());
        assertEquals(Status.ATIVO, pessoa.getStatus());
    }
}
