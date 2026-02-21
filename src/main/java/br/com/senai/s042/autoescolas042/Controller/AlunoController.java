package br.com.senai.s042.autoescolas042.Controller;

import br.com.senai.s042.autoescolas042.Domain.Alunos.Aluno;
import br.com.senai.s042.autoescolas042.Domain.Alunos.AlunoRepository;
import br.com.senai.s042.autoescolas042.Domain.Alunos.DadosCadastroAluno;
import br.com.senai.s042.autoescolas042.Domain.Alunos.DadosAtualizacaoAlunos;
import br.com.senai.s042.autoescolas042.Domain.Alunos.DadosListagemAlunos;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/alunos")
public class AlunoController {
    
    @Autowired
    private AlunoRepository alunoRepository;
    
    @PostMapping
    @Transactional
    public String cadastrarAluno(@RequestBody DadosCadastroAluno dadosAluno) {
        
        alunoRepository.save(new Aluno(dadosAluno));
        
        return "Cadastro realizado com sucesso! :)";
    }


    @GetMapping
    public ResponseEntity<Page<DadosListagemAlunos>> listarAlunos(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        Page page = alunoRepository.findAllByAtivoTrue(pageable).map(DadosListagemAlunos::new);
        return ResponseEntity.ok(page);
    }



    @PutMapping
    @Transactional
    public void atualizarAluno(@RequestBody DadosAtualizacaoAlunos dadosAtualizacaoAlunos){
        Aluno aluno = alunoRepository.getReferenceById(Long.valueOf(dadosAtualizacaoAlunos.id()));
        aluno.atualizarInformacoes(dadosAtualizacaoAlunos);
        alunoRepository.save(aluno);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirAluno(@PathVariable Long id){

        Aluno aluno = alunoRepository.getReferenceById(id);
        aluno.excluir();
        alunoRepository.save(aluno);
    }
}