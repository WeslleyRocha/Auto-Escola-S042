package br.com.senai.s042.autoescolas042.Domain.Instrucao;

import br.com.senai.s042.autoescolas042.Domain.Instrutor.Especialidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record  DadosAgendamentoInstrucao(

        @NotNull
        Long idAluno,

        Long idInstrutor,

        Especialidade especialidade,

        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
        LocalDateTime data) {
}
