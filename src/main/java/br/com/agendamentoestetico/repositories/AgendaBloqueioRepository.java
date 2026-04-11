package br.com.agendamentoestetico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agendamentoestetico.models.AgendaBloqueio;

public interface AgendaBloqueioRepository extends JpaRepository<AgendaBloqueio, Long> {

}
