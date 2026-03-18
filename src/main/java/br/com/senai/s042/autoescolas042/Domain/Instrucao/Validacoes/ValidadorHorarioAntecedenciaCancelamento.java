package br.com.senai.s042.autoescolas042.Domain.Instrucao.Validacoes;

import br.com.senai.s042.autoescolas042.Domain.Instrucao.DadosCancelamentoInstrucao;
import br.com.senai.s042.autoescolas042.Domain.Instrucao.InstrucaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedenciaCancelamento implements ValidadorCancelamentoDeInstrucao {

    @Autowired
    private InstrucaoRepository repository;

    @Override
    public void validar(DadosCancelamentoInstrucao dados) {
        var instrucao = repository.getReferenceById(dados.idInstrucao());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, instrucao.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("A instrução só pode ser cancelada com antecedência mínima de 24 horas.");
        }
    }
}