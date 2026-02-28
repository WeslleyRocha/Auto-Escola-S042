package br.com.senai.s042.autoescolas042.Infra.Security;

import br.com.senai.s042.autoescolas042.Domain.Usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    public String gerarToken (Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256("12345678");
            return JWT.create()
                    .withIssuer("Api Auto Escola S042")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw  new RuntimeException("Erro ao gerar token", exception);
        }
    }

    private Instant dataExpiracao() {

        return Instant.now().plusSeconds(90);
    }
}
