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

                        // Liberação de Options para o CORS
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // --- LIBERAÇÃO DO SWAGGER ---
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html")
                        .permitAll()
                        // ----------------------------

                        // 1. Rotas de Sempre liberadas
                        .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/procedimentos/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/agendamento/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/agendamento/horarios-disponiveis").permitAll()

                        // 2. Rotas de Moderação (Protegidas)
                        // Aqui TUDO exige autenticação
                        .requestMatchers("/procedimentos/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/auth/**").authenticated()
                        .requestMatchers("/agenda-trabalho/**").authenticated()
                        .requestMatchers("/agenda-bloqueio/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/auth/**").authenticated()

                        // Restrição por Role: Apenas GERENTE ou ADMIN podem deletar
                        .requestMatchers(HttpMethod.DELETE, "/procedimentos/**").hasAnyAuthority("ROLE_GERENTE", "ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/agendamento/**").hasAnyAuthority("ROLE_GERENTE", "ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/auth/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/auth/**").authenticated()

                        // Demais partes livres (à testar as funcionalidades)
                        .anyRequest().permitAll())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}