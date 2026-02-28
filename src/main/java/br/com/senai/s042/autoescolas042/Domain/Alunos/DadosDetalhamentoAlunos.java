package br.com.senai.s042.autoescolas042.Domain.Alunos;

import br.com.senai.s042.autoescolas042.Domain.Instrutor.Especialidade;

public record DadosDetalhamentoAlunos(
        Long id,
        String nome,
        String email,
        String cpf,
        Boolean ativo,
        Especialidade especialidade) {

    public DadosDetalhamentoAlunos(Aluno aluno) {

        this(aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getCpf(),
                aluno.getAtivo(),
                aluno.getEspecialidade()
        );
    }
}
