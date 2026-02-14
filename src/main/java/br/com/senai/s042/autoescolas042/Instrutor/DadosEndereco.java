package br.com.senai.s042.autoescolas042.Instrutor;

public record DadosEndereco(
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String cep,
        String uf) {
}
