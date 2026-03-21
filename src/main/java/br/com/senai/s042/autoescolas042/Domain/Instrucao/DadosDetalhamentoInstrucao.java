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
        LocalDateTime data,

        Boolean ativo,
        String motivo) {

    public DadosDetalhamentoInstrucao(Instrucao instrucao) {

        this(
                instrucao.getId(),
                instrucao.getAluno() != null ? instrucao.getAluno().getNome() : "N/A",
                instrucao.getInstrutor() != null ? instrucao.getInstrutor().getNome() : "Pendente",
                instrucao.getInstrutor() != null ? instrucao.getInstrutor().getEspecialidade() : null,
                instrucao.getData(),
                instrucao.getAtivo(),
                formatarMotivo(instrucao.getMotivo())
        );
    }

    private static String formatarMotivo(Motivo motivo) {
        if (motivo == null) {
            return "-";
        }
        return switch (motivo) {
            case INSTRUTOR_CANCELOU -> "Instrutor Cancelou";
            case ALUNO_DESISTIU -> "Aluno Cancelou";
            case OUTROS -> "Outros";
        };
    }
}
