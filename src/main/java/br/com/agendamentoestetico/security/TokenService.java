package br.com.agendamentoestetico.security;

import java.time.Instant;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.agendamentoestetico.models.Funcionario;



@Service
public class TokenService {

    private static final String ISSUER = "Sistema de Vendas API";

    @Value("${api.security.token.secret}")
    private String SECRET;

    public String gerarToken(Funcionario funcionario) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            String token = JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(funcionario.getEmail())
                    .withClaim("role", funcionario.getRole().name())
                    .withExpiresAt(gerarDataDeExpiracao())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token ", exception);
        }

    }

    public Instant gerarDataDeExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(java.time.ZoneOffset.of("-03:00"));
    }

    public String validaToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    public String extrairRole(String token) {
    try {
        Algorithm algoritmo = Algorithm.HMAC256(SECRET);
        return JWT.require(algoritmo)
                .withIssuer(ISSUER)
                .build()
                .verify(token)
                .getClaim("role") 
                .asString();
    } catch (JWTVerificationException exception) {
        return null;
    }
}

    




}
