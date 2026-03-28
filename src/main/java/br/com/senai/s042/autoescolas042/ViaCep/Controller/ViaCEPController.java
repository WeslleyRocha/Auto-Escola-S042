package br.com.senai.s042.autoescolas042.ViaCep.Controller;

import br.com.senai.s042.autoescolas042.ViaCep.Model.DadosConsultaCEP;
import br.com.senai.s042.autoescolas042.ViaCep.Model.DadosDetalhamentoCEP;
import br.com.senai.s042.autoescolas042.ViaCep.Service.ViaCEPService;
import br.com.senai.s042.autoescolas042.ViaCep.ViaCEPPortIn;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/viacep")
public class ViaCEPController implements ViaCEPPortIn {

    private final ViaCEPService viaCEPService;

    public ViaCEPController(ViaCEPService viaCEPServicem){
        this.viaCEPService = viaCEPServicem;
    }

    @Override
    @PostMapping
    public ResponseEntity<DadosDetalhamentoCEP> consultarCEP (@RequestBody @Valid DadosConsultaCEP dadosConsultaCEP) {

        return ResponseEntity.ok(viaCEPService.conssultar(dadosConsultaCEP));
    }
}
