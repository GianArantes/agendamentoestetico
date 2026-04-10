package br.com.agendamentoestetico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agendamentoestetico.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

}
