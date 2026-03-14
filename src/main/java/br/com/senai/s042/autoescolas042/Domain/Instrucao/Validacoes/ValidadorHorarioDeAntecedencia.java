package br.com.senai.s042.autoescolas042.Domain.Instrucao.Validacoes;

import br.com.senai.s042.autoescolas042.Domain.Instrucao.DadosAgendamentoInstrucao;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioDeAntecedencia implements ValidadorAgendamento{

    @Override
    public void validar(DadosAgendamentoInstrucao dadosAgendamentoInstrucao) {

        LocalDateTime horarioAgora = LocalDateTime.now();
        LocalDateTime horaAgendamento = dadosAgendamentoInstrucao.data();

        Long antecedencia = Duration.between(horarioAgora, horaAgendamento).toMillis();

        if (antecedencia  < 30){
            throw new ValidacaoException("Agendamento deve ser feito com 30 min antes do inicio da Aula !");
        }
    }

}

