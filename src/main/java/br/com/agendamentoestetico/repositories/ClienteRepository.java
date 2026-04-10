package br.com.agendamentoestetico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agendamentoestetico.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
