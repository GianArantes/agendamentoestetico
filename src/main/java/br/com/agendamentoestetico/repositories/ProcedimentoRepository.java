package br.com.agendamentoestetico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agendamentoestetico.model.Procedimento;

public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long> {

}
