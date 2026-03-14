package br.com.senai.s042.autoescolas042.Domain.Instrucao.Validacoes;

import br.com.senai.s042.autoescolas042.Domain.Instrucao.DadosAgendamentoInstrucao;
import br.com.senai.s042.autoescolas042.Domain.Instrutor.InstrutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorInstrutorAtivo implements ValidadorAgendamento {

    @Autowired
    private InstrutorRepository instrutorRepository;

    @Override
    public void validar(DadosAgendamentoInstrucao dadosAgendamentoInstrucao) {

        Boolean instrutorAtivo = instrutorRepository.findArivoById(dadosAgendamentoInstrucao.idInstrutor());

        if (!instrutorAtivo){
            throw new ValidacaoException("Agendamento não pode ser realizado com o Instrutor inativo!");
        }

    }
}
