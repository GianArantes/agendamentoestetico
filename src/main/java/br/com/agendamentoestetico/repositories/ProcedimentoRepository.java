package br.com.agendamentoestetico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agendamentoestetico.models.Procedimento;

public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long> {

}
