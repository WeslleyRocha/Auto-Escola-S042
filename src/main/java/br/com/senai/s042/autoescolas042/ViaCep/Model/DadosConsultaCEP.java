package br.com.senai.s042.autoescolas042.ViaCep.Model;

import jakarta.validation.constraints.NotBlank;

public record DadosConsultaCEP (

        @NotBlank
        String cep){
}
