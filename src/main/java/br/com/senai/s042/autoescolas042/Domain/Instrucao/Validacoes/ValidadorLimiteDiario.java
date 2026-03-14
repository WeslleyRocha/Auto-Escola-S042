package br.com.senai.s042.autoescolas042.Domain.Instrucao.Validacoes;

import br.com.senai.s042.autoescolas042.Domain.Instrucao.DadosAgendamentoInstrucao;
import br.com.senai.s042.autoescolas042.Domain.Instrucao.InstrucaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidadorLimiteDiario implements ValidadorAgendamento{

    @Autowired
    private InstrucaoRepository instrucaoRepository;

    @Override
    public void validar(DadosAgendamentoInstrucao dadosAgendamentoInstrucao) {

        LocalDateTime inicioExpediente = dadosAgendamentoInstrucao.data().withHour(6);
        LocalDateTime fimExpediente = dadosAgendamentoInstrucao.data().withHour(21 - 1);

        Boolean reincidenciaDiaria = instrucaoRepository.existsByAlunoIdAndDataBetween(
                dadosAgendamentoInstrucao.idAluno(),
                inicioExpediente,
                fimExpediente);

        if(reincidenciaDiaria){
            throw new ValidacaoException("Só é permitido um agendamento uma aula por dia, para cada aluno!");
        }
    }
}
