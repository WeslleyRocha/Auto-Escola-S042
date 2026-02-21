package br.com.senai.s042.autoescolas042.Domain.Alunos;

import br.com.senai.s042.autoescolas042.Domain.Endereco.DadosEndereco;

public record DadosAtualizacaoAlunos(
        String id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
