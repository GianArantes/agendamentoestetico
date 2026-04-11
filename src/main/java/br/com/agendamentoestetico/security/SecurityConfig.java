package br.com.agendamentoestetico.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    SecurityFilter securityFilter;

   @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .cors(Customizer.withDefaults())
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(RequestMethod.OPTIONS.name(), "/**").permitAll()

            // 1. Rotas de Autenticação (Sempre liberadas)
            .requestMatchers(HttpMethod.POST, "/login", "/register").permitAll()

            // 2. Rotas de Moderação (Protegidas)
            // Aqui você diz que TUDO que começa com /funcionario/ exige autenticação
            .requestMatchers("/funcionario/**").authenticated()
            
            // 3. Validação por E-mail (Pública, pois o usuário ainda não está logado)
            .requestMatchers(HttpMethod.POST, "/agenda/validar-codigo").permitAll()

            // 4. Todo o resto da Agenda (Público)
            // Se a maior parte da API é pública, usamos o permitAll() no final
            .anyRequest().permitAll()
        )
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }



}