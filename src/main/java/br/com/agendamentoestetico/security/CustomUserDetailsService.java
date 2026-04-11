package br.com.agendamentoestetico.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.agendamentoestetico.models.Funcionario;
import br.com.agendamentoestetico.repositories.FuncionarioRepository;

import org.springframework.security.core.userdetails.User;

@Component
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Funcionario funcionario = this.funcionarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + email));

        return new User(funcionario.getEmail(), funcionario.getSenha(),
                new ArrayList<>());
    }



}
