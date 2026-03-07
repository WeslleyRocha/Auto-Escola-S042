package br.com.senai.s042.autoescolas042.Domain.Instrucao;

import br.com.senai.s042.autoescolas042.Domain.Instrutor.Especialidade;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DadosDetalhamentoInstrucao(
        Long id,
        String aluno,
        String instrutor,
        Especialidade especialidade,

        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
        LocalDateTime data) {

    public DadosDetalhamentoInstrucao(Instrucao instrucao) {

        this(
                instrucao.getId(),
                instrucao.getAluno().getNome(),
                instrucao.getInstrutor().getNome(),
                instrucao.getInstrutor().getEspecialidade(),
                instrucao.getData()
        );
    }
}
