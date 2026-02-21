package br.com.senai.s042.autoescolas042.Domain.Alunos;

import br.com.senai.s042.autoescolas042.Domain.Instrutor.Especialidade;

public record DadosListagemAlunos(
        Long id,
        String nome,
        String email,
        String cpf,
        Especialidade especialidade) {

    public DadosListagemAlunos(Aluno aluno) {
        this(aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getCpf(),
                aluno.getEspecialidade()
        );
    }
}
