package br.com.agendamentoestetico.security;

import br.com.agendamentoestetico.models.Funcionario;
import br.com.agendamentoestetico.models.enums.Status;
import br.com.agendamentoestetico.models.enums.UserRole;
import br.com.agendamentoestetico.repositories.FuncionarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(FuncionarioRepository repository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Verifica se já existe um admin para não duplicar toda vez que iniciar
            if (repository.count() == 0) {
                Funcionario funcionario = new Funcionario();
                funcionario.setEmail("gian_arantes@hotmail.com");
                funcionario.setSenha(passwordEncoder.encode("admin")); // criptografa a senha
                funcionario.setNome("Admin");
                funcionario.setCelular("13991953399");
                funcionario.setStatus(Status.ATIVO);
                funcionario.setRole(UserRole.ROLE_ADMIN);
                repository.save(funcionario);
                System.out.println(">>> Usuário ADMIN criado com sucesso!");
            } else {
                System.out.println(">>> Usuário ADMIN já existe no banco.");
            }
        };
    }
}