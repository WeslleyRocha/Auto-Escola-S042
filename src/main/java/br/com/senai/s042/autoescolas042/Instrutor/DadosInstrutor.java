package br.com.senai.s042.autoescolas042.Instrutor;

public record DadosInstrutor(String nome,
                           String email,
                           String telefone,
                           String cnh,
                           Especialidade especialidade,
                           DadosEndereco endereco) {
}