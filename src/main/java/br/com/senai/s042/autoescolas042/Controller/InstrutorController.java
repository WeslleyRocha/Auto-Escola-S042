package br.com.senai.s042.autoescolas042.Controller;

import br.com.senai.s042.autoescolas042.Domain.Instrutor.DadosAtualizacaoInstrutor;
import br.com.senai.s042.autoescolas042.Domain.Instrutor.DadosDetalhamentoInstrutor;
import br.com.senai.s042.autoescolas042.Domain.Instrutor.DadosCadastroInstrutor;
import br.com.senai.s042.autoescolas042.Domain.Instrutor.DadosListagemInstrutors;
import br.com.senai.s042.autoescolas042.Domain.Instrutor.Instrutor;
import br.com.senai.s042.autoescolas042.Domain.Instrutor.InstrutorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/instrutores")
public class InstrutorController {

    @Autowired
    private InstrutorRepository instrutorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoInstrutor> cadastrarInstrutor(@RequestBody @Valid
                                                                         DadosCadastroInstrutor dadosInstrutor,
                                                                         UriComponentsBuilder uriBuilder) {
        Instrutor instrutor = new Instrutor(dadosInstrutor);
        instrutorRepository.save(instrutor);
        URI uri = uriBuilder.path("/instrutores/{id}")
                .buildAndExpand(instrutor.getId()).toUri();

       return ResponseEntity.created(uri)
               .body(new DadosDetalhamentoInstrutor(instrutor));
    }
    
    @GetMapping
    public ResponseEntity<Page<DadosListagemInstrutors>>listarInstrutores(
        @PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        Page page = instrutorRepository.findAllByAtivoTrue(pageable).map(DadosListagemInstrutors::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoInstrutor> detalharInstrutor(@PathVariable Long id){
        Instrutor instrutor = instrutorRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoInstrutor(instrutor));
    }

    @PutMapping
    @Transactional
    public void atualizarInstrutor(@RequestBody DadosAtualizacaoInstrutor dadosAtualizacaoInstrutor){
        Instrutor instrutor = instrutorRepository.getReferenceById(Long.valueOf(dadosAtualizacaoInstrutor.id()));
        instrutor.atualizarInformacoes(dadosAtualizacaoInstrutor);
        instrutorRepository.save(instrutor);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirInstrutor(@PathVariable Long id){

        Instrutor instrutor = instrutorRepository.getReferenceById(id);
        instrutor.excluir();
        instrutorRepository.save(instrutor);
        return ResponseEntity.noContent().build();
    }
}
