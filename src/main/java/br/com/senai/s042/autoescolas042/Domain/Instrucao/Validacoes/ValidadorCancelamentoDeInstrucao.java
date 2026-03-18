package br.com.senai.s042.autoescolas042.Domain.Instrucao.Validacoes;

import br.com.senai.s042.autoescolas042.Domain.Instrucao.DadosCancelamentoInstrucao;

public interface ValidadorCancelamentoDeInstrucao {
    void validar(DadosCancelamentoInstrucao dados);
}