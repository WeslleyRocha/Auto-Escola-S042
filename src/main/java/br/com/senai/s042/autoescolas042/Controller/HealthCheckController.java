package br.com.senai.s042.autoescolas042.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health-check")
public class HealthCheckController {

    @GetMapping()
    public String healthCheck() {

            return "Verificação de integridade da API da AutoEscolaS042: API está funcionando corretamente! ;) ";
    }
}
