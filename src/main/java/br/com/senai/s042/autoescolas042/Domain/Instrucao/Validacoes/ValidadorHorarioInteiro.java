package br.com.senai.s042.autoescolas042.Domain.Instrucao.Validacoes;

import br.com.senai.s042.autoescolas042.Domain.Instrucao.DadosAgendamentoInstrucao;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidadorHorarioInteiro implements ValidadorAgendamento{

    @Override
    public void validar(DadosAgendamentoInstrucao dadosAgendamentoInstrucao) {

        LocalDateTime dataAgendamento = dadosAgendamentoInstrucao.data();

        if(dataAgendamento.getMinute() != 0 || dataAgendamento.getSecond() != 0) {

            throw new ValidacaoException("Horaio invalido, (Ex: 08h as 13h)");
        }

    }
}
