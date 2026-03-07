package br.com.senai.s042.autoescolas042.Domain.Instrucao;

import br.com.senai.s042.autoescolas042.Domain.Alunos.Aluno;
import br.com.senai.s042.autoescolas042.Domain.Instrutor.Especialidade;
import br.com.senai.s042.autoescolas042.Domain.Instrutor.Instrutor;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Instrucao")
@Table(name = "instrucoes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Instrucao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instrutor_id")
    private Instrutor instrutor;

//    @Enumerated
//    private Especialidade especialidade;
   private LocalDateTime data;
}
