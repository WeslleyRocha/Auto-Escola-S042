package br.com.senai.s042.autoescolas042.Controller;

import br.com.senai.s042.autoescolas042.Domain.Instrutor.DadosAtualizacaoInstrutor;
import br.com.senai.s042.autoescolas042.Domain.Instrutor.DadosDetalhamentoInstrutor;
import br.com.senai.s042.autoescolas042.Domain.Instrutor.DadosCadastroInstrutor;
import br.com.senai.s042.autoescolas042.Domain.Instrutor.DadosListagemInstrutors;
import br.com.senai.s042.autoescolas042.Domain.Instrutor.InstrutorService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

    private final InstrutorService instrutorService;

    public InstrutorController(InstrutorService instrutorService) {
        this.instrutorService = instrutorService;
    }

    @PostMapping
    public ResponseEntity<DadosDetalhamentoInstrutor> cadastrarInstrutor(@RequestBody @Valid
                                                                         DadosCadastroInstrutor dadosInstrutor,
                                                                         UriComponentsBuilder uriBuilder) {

        DadosDetalhamentoInstrutor dto = instrutorService.cadastrar(dadosInstrutor);

        URI uri = uriBuilder.path("/instrutores/{id}").buildAndExpand(dto.id()).toUri();

       return ResponseEntity.created(uri).body(dto);
    }
    
    @GetMapping
    public ResponseEntity<Page<DadosListagemInstrutors>> listarInstrutores(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){

        return ResponseEntity.ok(instrutorService.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoInstrutor> detalharInstrutor(@PathVariable Long id){

        DadosDetalhamentoInstrutor dto = instrutorService.detalhar(id);

        return ResponseEntity.ok(dto);
    }

    @PutMapping
    public ResponseEntity<DadosDetalhamentoInstrutor> atualizarInstrutor(
            @RequestBody DadosAtualizacaoInstrutor dadosAtualizacaoInstrutor){

        DadosDetalhamentoInstrutor dto = instrutorService.atualizar(dadosAtualizacaoInstrutor);

      return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirInstrutor(@PathVariable Long id){

       instrutorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
