package br.com.agendamentoestetico.security;

import java.io.IOException;
import java.util.Collections;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    
    private TokenService tokenService;    


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var token = this.recoverToken(request);
        var email = this.tokenService.validaToken(token);

        if (email != null) {
           
            String role = this.tokenService.extrairRole(token);

            // 1. Transformar a String da Role em um objeto SimpleGrantedAuthority
            // O Spring espera "ROLE_ADMIN", "ROLE_USER", etc.
            var authority = new SimpleGrantedAuthority(role);

            // 2. Colocar dentro de uma lista (o construtor do Token exige uma coleção)
            var authorities = Collections.singletonList(authority);

            // 3. Agora o construtor aceitará o parâmetro
            var authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");

        // Verifica se o cabeçalho existe e se começa com "Bearer "
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return null;
        }

        // Remove os primeiros 7 caracteres ("Bearer ")
        return authorizationHeader.substring(7);
    }

}
