package br.com.senai.s042.autoescolas042.Domain.Alunos;

public class AlunoNaoExisteException extends RuntimeException {
    public AlunoNaoExisteException(String message) {
        super(message);
    }
}
