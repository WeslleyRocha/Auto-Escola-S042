package br.com.senai.s042.autoescolas042.Domain.Alunos;

import br.com.senai.s042.autoescolas042.Domain.Endereco.DadosEndereco;
import br.com.senai.s042.autoescolas042.Domain.Instrutor.Especialidade;

public record DadosCadastroAluno(
        String nome,
        String email,
        String telefone,
        String cpf,
        Especialidade especialidade,
        DadosEndereco endereco
) {
}
