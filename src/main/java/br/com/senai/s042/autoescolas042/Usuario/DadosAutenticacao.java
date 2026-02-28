package br.com.senai.s042.autoescolas042.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public record DadosAutenticacao(

        @NotBlank
        String login,

        @NotBlank
        //@Pattern(regexp = ".{8}")
        String senha) {
}
