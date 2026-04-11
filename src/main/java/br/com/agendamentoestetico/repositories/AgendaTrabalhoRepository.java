package br.com.agendamentoestetico.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agendamentoestetico.models.AgendaTrabalho;
import br.com.agendamentoestetico.models.enums.DiaDaSemana;

public interface AgendaTrabalhoRepository extends JpaRepository<AgendaTrabalho, Long> {

public Optional<AgendaTrabalho> findByFuncionarioIdAndDiaDaSemana(Long funcionarioId, DiaDaSemana diaDaSemana);

}
