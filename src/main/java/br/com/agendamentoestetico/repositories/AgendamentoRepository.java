package br.com.agendamentoestetico.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agendamentoestetico.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findAllByFuncionarioIdAndInicioProcedimentoBetween(
            Long funcionarioId,
            LocalDateTime inicio,
            LocalDateTime fim);
}
