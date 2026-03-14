package br.com.senai.s042.autoescolas042.Domain.Instrucao;

import br.com.senai.s042.autoescolas042.Domain.Alunos.Aluno;
import br.com.senai.s042.autoescolas042.Domain.Alunos.AlunoNaoExisteException;
import br.com.senai.s042.autoescolas042.Domain.Alunos.AlunoRepository;
import br.com.senai.s042.autoescolas042.Domain.Instrucao.Validacoes.ValidadorAgendamento;
import br.com.senai.s042.autoescolas042.Domain.Instrutor.Instrutor;
import br.com.senai.s042.autoescolas042.Domain.Instrutor.InstrutorNaoExisteException;
import br.com.senai.s042.autoescolas042.Domain.Instrutor.InstrutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeInstrucoes {
    
    private final InstrucaoRepository instrucaoRepository;
    private final AlunoRepository alunoRepository;
    private final InstrutorRepository instrutorRepository;
    private final List<ValidadorAgendamento> validadoresAgentamento;

    public AgendaDeInstrucoes (InstrucaoRepository instrucaoRepository, 
                               AlunoRepository alunoRepository,
                               InstrutorRepository instrutorRepository, 
                               List<ValidadorAgendamento> validadoresAgentamento){
        this.instrucaoRepository = instrucaoRepository;
        this.alunoRepository = alunoRepository;
        this.instrutorRepository = instrutorRepository;
        this.validadoresAgentamento = validadoresAgentamento;
    }
    

    @Transactional
    public DadosDetalhamentoInstrucao agendarInstrucao(DadosAgendamentoInstrucao dadosAgendamentoInstrucao){

        if (!alunoRepository.existsById(dadosAgendamentoInstrucao.idAluno())){

            throw new AlunoNaoExisteException("Id do aluno não encontrado!");
        }

        if (dadosAgendamentoInstrucao.idInstrutor() != null && !instrutorRepository.existsById(dadosAgendamentoInstrucao.idInstrutor())){
            throw new InstrutorNaoExisteException("Id do instrutor não encontrado!");
        }

        //Validacoes das regras de Negocio
        validadoresAgentamento.forEach(v -> v.validar(dadosAgendamentoInstrucao));



        Aluno aluno = alunoRepository.getReferenceById(dadosAgendamentoInstrucao.idAluno());

        Instrutor instrutor = escolherInstrutor(dadosAgendamentoInstrucao);

        if (instrutor ==  null){
            throw new InstrutorIndisponivelException("Não há instrutor disponivel para agemdamento! ");

        }

        Instrucao instrucao = new Instrucao(
                null,
                aluno,
                instrutor,
                dadosAgendamentoInstrucao.data()
        );

        instrucaoRepository.save(instrucao);

        return new DadosDetalhamentoInstrucao(instrucao);
    }

    private Instrutor escolherInstrutor(DadosAgendamentoInstrucao dadosAgendamentoInstrucao) {

        if (dadosAgendamentoInstrucao.idInstrutor() != null) {

            return instrutorRepository.getReferenceById(dadosAgendamentoInstrucao.idInstrutor());
        }

        if(dadosAgendamentoInstrucao.especialidade() == null) {

            throw new EspecialidadeNaoInformada("Especialidade é obrigatoria quando o Instrutor não for informado!");
        }

        return instrutorRepository.escolherInstrutorAleatorioDisponivel(dadosAgendamentoInstrucao.especialidade(),dadosAgendamentoInstrucao.data());
    }
}