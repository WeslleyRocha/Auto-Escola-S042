package br.com.senai.s042.autoescolas042.Domain.Instrutor;

import br.com.senai.s042.autoescolas042.Domain.Endereco.Endereco;

public record DadosDetalhamentoInstrutor(
        Long id,
        String nome,
        String email,
        String telefone,
        String cnh,
        Boolean ativo,
        Especialidade especialidade,
        Endereco endereco) {

    public DadosDetalhamentoInstrutor(Instrutor instrutor) {

        this(instrutor.getId(),
                instrutor.getNome(),
                instrutor.getEmail(),
                instrutor.getTelefone(),
                instrutor.getCnh(),
                instrutor.getAtivo(),
                instrutor.getEspecialidade(),
                instrutor.getEndereco()
        );
    }
}