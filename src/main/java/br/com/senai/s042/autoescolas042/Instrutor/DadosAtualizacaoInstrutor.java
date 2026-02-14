package br.com.senai.s042.autoescolas042.Instrutor;

public record DadosAtualizacaoInstrutor(
        String id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
