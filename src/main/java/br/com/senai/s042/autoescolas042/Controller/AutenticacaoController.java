package br.com.senai.s042.autoescolas042.Controller;

import br.com.senai.s042.autoescolas042.Infra.Security.DadosTokenJWT;
import br.com.senai.s042.autoescolas042.Infra.Security.TokenService;
import br.com.senai.s042.autoescolas042.Domain.AutenticacaoService.DadosAutenticacao;
import br.com.senai.s042.autoescolas042.Domain.Usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid DadosAutenticacao dadosAutenticacao){

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                dadosAutenticacao.login(), dadosAutenticacao.senha());

        Authentication authentication = authenticationManager.authenticate(token);

        String tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        System.out.println("Autenticacao efetuada");
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
