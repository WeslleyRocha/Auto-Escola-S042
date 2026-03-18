package br.com.senai.s042.autoescolas042.Domain.Instrucao;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoInstrucao(
        @NotNull
        Long idInstrucao,

        Motivo motivo
) {
}