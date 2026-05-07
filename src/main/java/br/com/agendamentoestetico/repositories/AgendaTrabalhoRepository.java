package br.com.agendamentoestetico.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agendamentoestetico.models.AgendaTrabalho;
import br.com.agendamentoestetico.models.enums.DiaDaSemana;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AgendaTrabalhoRepository extends JpaRepository<AgendaTrabalho, Long> {

    public Optional<AgendaTrabalho> findByFuncionarioIdAndDiaDaSemana(Long funcionarioId, DiaDaSemana diaDaSemana);


    public List<AgendaTrabalho> findByFuncionarioId(@Param("id") long id);

}
