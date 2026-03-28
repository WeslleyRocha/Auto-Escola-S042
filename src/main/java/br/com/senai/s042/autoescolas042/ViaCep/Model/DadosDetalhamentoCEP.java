package br.com.senai.s042.autoescolas042.ViaCep.Model;

public record DadosDetalhamentoCEP(

        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf) {
}
