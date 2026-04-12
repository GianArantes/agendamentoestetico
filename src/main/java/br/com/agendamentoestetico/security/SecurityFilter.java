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

        // Se o token existe, tentamos autenticar o usuário
        if (token != null) {
            var email = this.tokenService.validaToken(token);

            if (email != null) {
                String role = this.tokenService.extrairRole(token);
                var authority = new SimpleGrantedAuthority(role);
                var authorities = Collections.singletonList(authority);

                var authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // O filterChain.doFilter DEVE estar fora do if(token != null)
        // Se o token for null, ele apenas segue o fluxo e o SecurityConfig decide o
        // resto
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
