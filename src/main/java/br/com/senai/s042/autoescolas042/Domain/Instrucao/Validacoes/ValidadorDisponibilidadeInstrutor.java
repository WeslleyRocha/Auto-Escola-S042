package br.com.senai.s042.autoescolas042.Domain.Instrucao.Validacoes;

import br.com.senai.s042.autoescolas042.Domain.Instrucao.DadosAgendamentoInstrucao;
import br.com.senai.s042.autoescolas042.Domain.Instrucao.InstrucaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDisponibilidadeInstrutor implements ValidadorAgendamento{

    @Autowired
    private InstrucaoRepository instrucaoRepository;

    @Override
    public void validar(DadosAgendamentoInstrucao dadosAgendamentoInstrucao) {

        Boolean instrutorOcupado = instrucaoRepository.existsByInstrutorIdAndData(
                dadosAgendamentoInstrucao.idInstrutor(),
                dadosAgendamentoInstrucao.data());

        if(instrutorOcupado){
            throw new ValidacaoException("O instrutor já possui outra aula, agendado nesse horário");
        }
    }
}
