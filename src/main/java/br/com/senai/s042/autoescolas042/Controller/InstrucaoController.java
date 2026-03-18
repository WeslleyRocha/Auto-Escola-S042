package br.com.senai.s042.autoescolas042.Controller;

import br.com.senai.s042.autoescolas042.Domain.Instrucao.AgendaDeInstrucoes;
import br.com.senai.s042.autoescolas042.Domain.Instrucao.DadosAgendamentoInstrucao;
import br.com.senai.s042.autoescolas042.Domain.Instrucao.DadosCancelamentoInstrucao;
import br.com.senai.s042.autoescolas042.Domain.Instrucao.DadosDetalhamentoInstrucao;
import br.com.senai.s042.autoescolas042.Domain.Instrucao.InstrucaoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instrucao")
public class InstrucaoController {

    @Autowired
    private AgendaDeInstrucoes agendaDeInstrucoes;

    @Autowired
    private InstrucaoRepository instrucaoRepository;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity <DadosDetalhamentoInstrucao> agendarInstrucao(
            @RequestBody
            @Valid
            DadosAgendamentoInstrucao dadosAgendamentoInstrucao){

        DadosDetalhamentoInstrucao dto = agendaDeInstrucoes.agendarInstrucao(dadosAgendamentoInstrucao);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Page<DadosDetalhamentoInstrucao>> listarInstrucao(
            @PageableDefault(size = 10 , sort = {"id"}) Pageable pageable) {

        Page page = instrucaoRepository.findAll(pageable).map(DadosDetalhamentoInstrucao::new);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Void> excluirInstrucao(
            @RequestBody
            @Valid
            DadosCancelamentoInstrucao dadosCancelamentoInstrucao
    ){
        agendaDeInstrucoes.cancelarInstrucao(dadosCancelamentoInstrucao);
        return ResponseEntity.noContent().build();
    }
}
