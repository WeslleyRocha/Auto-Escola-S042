package br.com.senai.s042.autoescolas042.Domain.Instrutor;

public class InstrutorNaoExisteException extends RuntimeException {
    public InstrutorNaoExisteException(String message) {
        super(message);
    }
}
