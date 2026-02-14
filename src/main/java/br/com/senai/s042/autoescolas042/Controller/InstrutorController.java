package br.com.senai.s042.autoescolas042.Controller;

import br.com.senai.s042.autoescolas042.Instrutor.DadosAtualizacaoInstrutor;
import br.com.senai.s042.autoescolas042.Instrutor.DadosInstrutor;
import br.com.senai.s042.autoescolas042.Instrutor.DadosListagemInstrutors;
import br.com.senai.s042.autoescolas042.Instrutor.Instrutor;
import br.com.senai.s042.autoescolas042.Instrutor.InstrutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/instrutores")
public class InstrutorController {

    @Autowired
    private InstrutorRepository instrutorRepository;

    @PostMapping
    @Transactional
    public String cadastrarInstrutor(@RequestBody DadosInstrutor dadosInstrutor) {

        instrutorRepository.save(new Instrutor(dadosInstrutor));

       return "Cadastro realizado com sucesso! :)";
    }
    
    
    @GetMapping
    public List<DadosListagemInstrutors> listarInstrutores(){
        return instrutorRepository.findAll().stream().map(DadosListagemInstrutors::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizarInstrutor(@RequestBody DadosAtualizacaoInstrutor dadosAtualizacaoInstrutor){
        Instrutor instrutor = instrutorRepository.getReferenceById(Long.valueOf(dadosAtualizacaoInstrutor.id()));
        instrutor.atualizarInformacoes(dadosAtualizacaoInstrutor);
        instrutorRepository.save(instrutor);
    }

    @DeleteMapping("/{id}")
    public void excluirInstrutor(@PathVariable Long id){

        instrutorRepository.deleteById(id);
        System.out.println("Instrutor excluído com sucesso!");
    }
}
