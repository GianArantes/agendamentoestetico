package br.com.agendamentoestetico.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.agendamentoestetico.models.Pessoa;
import br.com.agendamentoestetico.models.enums.Status;

class PessoaTestImpl extends Pessoa {}

class PessoaTest {
    @Test
    void deveCriarPessoaComDadosCorretos() {
        Pessoa pessoa = new PessoaTestImpl();
        pessoa.setNome("Teste");
        pessoa.setEmail("teste@email.com");
        pessoa.setCelular("11955555555");
        pessoa.setStatus(Status.ATIVO);
        assertEquals("Teste", pessoa.getNome());
        assertEquals("teste@email.com", pessoa.getEmail());
        assertEquals("11955555555", pessoa.getCelular());
        assertEquals(Status.ATIVO, pessoa.getStatus());
    }
}
