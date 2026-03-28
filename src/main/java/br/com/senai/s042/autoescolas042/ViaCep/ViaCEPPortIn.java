package br.com.senai.s042.autoescolas042.ViaCep;

import br.com.senai.s042.autoescolas042.ViaCep.Model.DadosConsultaCEP;
import br.com.senai.s042.autoescolas042.ViaCep.Model.DadosDetalhamentoCEP;
import org.springframework.http.ResponseEntity;

public interface ViaCEPPortIn {

    ResponseEntity<DadosDetalhamentoCEP> consultarCEP (DadosConsultaCEP dadosConsultaCEP);

}
