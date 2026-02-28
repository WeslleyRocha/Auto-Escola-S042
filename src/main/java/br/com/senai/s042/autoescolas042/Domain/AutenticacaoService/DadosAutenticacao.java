package br.com.senai.s042.autoescolas042.Domain.AutenticacaoService;

import jakarta.validation.constraints.NotBlank;


public record DadosAutenticacao(

        @NotBlank
        String login,

        @NotBlank
        //@Pattern(regexp = ".{8}")
        String senha) {
}
