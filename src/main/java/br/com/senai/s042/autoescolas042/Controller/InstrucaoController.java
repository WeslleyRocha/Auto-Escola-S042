package br.com.senai.s042.autoescolas042.Controller;

import br.com.senai.s042.autoescolas042.Domain.Instrucao.AgendaDeInstrucoes;
import br.com.senai.s042.autoescolas042.Domain.Instrucao.DadosAgendamentoInstrucao;
import br.com.senai.s042.autoescolas042.Domain.Instrucao.DadosDetalhamentoInstrucao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instrucao")
public class InstrucaoController {

    @Autowired
    private AgendaDeInstrucoes agendaDeInstrucoes;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity <DadosDetalhamentoInstrucao> agendarInstrucao(
            @RequestBody
            @Valid
            DadosAgendamentoInstrucao dadosAgendamentoInstrucao){

        DadosDetalhamentoInstrucao dto = agendaDeInstrucoes.agendarInstrucao(dadosAgendamentoInstrucao);

        return ResponseEntity.ok(dto);
    }
}
