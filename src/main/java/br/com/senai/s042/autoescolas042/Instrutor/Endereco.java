package br.com.senai.s042.autoescolas042.Instrutor;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Endereco {

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;


public Endereco(DadosEndereco dadosEndereco){
    this.logradouro = dadosEndereco.logradouro();
    this.numero = dadosEndereco.numero();
    this.complemento = dadosEndereco.complemento();
    this.bairro = dadosEndereco.bairro();
    this.cidade = dadosEndereco.cidade();
    this.uf = dadosEndereco.uf();
    this.cep = dadosEndereco.cep();
 }

    public void atualizarInformacoes(DadosEndereco dadosEndereco) {

         if (dadosEndereco.logradouro() !=null && !dadosEndereco.logradouro().isBlank() && !dadosEndereco.logradouro().isEmpty()){
             this.logradouro = dadosEndereco.logradouro();
        }

        if (dadosEndereco.numero() !=null && !dadosEndereco.numero().isBlank() && !dadosEndereco.numero().isEmpty()){
            this.numero = dadosEndereco.numero();
        }

        if (dadosEndereco.complemento() !=null && !dadosEndereco.complemento().isBlank() && !dadosEndereco.complemento().isEmpty()){
            this.complemento = dadosEndereco.complemento();
        }

        if (dadosEndereco.bairro() !=null && !dadosEndereco.bairro().isBlank() && !dadosEndereco.bairro().isEmpty()){
            this.bairro = dadosEndereco.bairro();
        }

        if (dadosEndereco.cidade() !=null && !dadosEndereco.cidade().isBlank() && !dadosEndereco.cidade().isEmpty()){
            this.cidade = dadosEndereco.cidade();
        }

        if (dadosEndereco.uf() !=null && !dadosEndereco.uf().isBlank() && !dadosEndereco.uf().isEmpty()){
            this.uf = dadosEndereco.uf();
        }

        if (dadosEndereco.cep() !=null && !dadosEndereco.cep().isBlank() && !dadosEndereco.cep().isEmpty()){
            this.cep = dadosEndereco.cep();
        }
    }
}
