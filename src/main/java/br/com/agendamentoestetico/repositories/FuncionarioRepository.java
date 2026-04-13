package br.com.agendamentoestetico.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.agendamentoestetico.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    public Optional<Funcionario> findByEmail(String email);

    public boolean existsByEmail(String email);

    public Optional<Funcionario> findByResetSenhaToken(String token);

}
