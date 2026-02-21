package br.com.senai.s042.autoescolas042.Instrutor;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Instrutor")
@Table(name = "instrutores")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Instrutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cnh;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;
    private Boolean ativo = true;

    public Instrutor(DadosInstrutor dadosInstrutor) {
        this.nome = dadosInstrutor.nome();
        this.email = dadosInstrutor.email();
        this.telefone = dadosInstrutor.telefone();
        this.cnh = dadosInstrutor.cnh();
        this.especialidade = dadosInstrutor.especialidade();
        this.endereco = new Endereco(dadosInstrutor.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoInstrutor dadosAtualizacaoInstrutor) {

        if(dadosAtualizacaoInstrutor.nome() != null && !dadosAtualizacaoInstrutor.nome().isBlank()){
            this.nome = dadosAtualizacaoInstrutor.nome();

        } if(dadosAtualizacaoInstrutor.telefone() != null && !dadosAtualizacaoInstrutor.telefone().isBlank()){
            this.telefone = dadosAtualizacaoInstrutor.telefone();

        } if(dadosAtualizacaoInstrutor.endereco() != null){
            this.endereco.atualizarInformacoes(dadosAtualizacaoInstrutor.endereco());
        }
    }

    public void excluir(){
        this.ativo = false;
    }
}
