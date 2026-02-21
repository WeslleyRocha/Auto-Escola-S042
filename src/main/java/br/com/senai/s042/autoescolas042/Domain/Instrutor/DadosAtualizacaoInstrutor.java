package br.com.senai.s042.autoescolas042.Domain.Instrutor;

import br.com.senai.s042.autoescolas042.Domain.Endereco.DadosEndereco;

public record DadosAtualizacaoInstrutor(
        String id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
