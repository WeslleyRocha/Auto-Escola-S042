package br.com.senai.s042.autoescolas042.Domain.Instrucao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface InstrucaoRepository extends JpaRepository<Instrucao, Long> {

    Boolean existsByAlunoIdAndDataBetween(Long id, LocalDateTime inicio, LocalDateTime fim);

    Boolean existsByInstrutorIdAndData(Long id, LocalDateTime data);
}
