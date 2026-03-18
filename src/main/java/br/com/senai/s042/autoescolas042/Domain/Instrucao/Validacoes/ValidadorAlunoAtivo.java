package br.com.senai.s042.autoescolas042.Domain.Instrucao.Validacoes;

import br.com.senai.s042.autoescolas042.Domain.Alunos.AlunoRepository;
import br.com.senai.s042.autoescolas042.Domain.Instrucao.DadosAgendamentoInstrucao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAlunoAtivo implements ValidadorAgendamento {

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public void validar(DadosAgendamentoInstrucao dadosAgendamentoInstrucao) {

        Boolean alunoAtivo = alunoRepository.findAtivoById(dadosAgendamentoInstrucao.idAluno());

        if (!alunoAtivo){
            throw new ValidacaoException("Agendamento não pode ser realizado com o Aluno inativo!");
        }

    }
}
