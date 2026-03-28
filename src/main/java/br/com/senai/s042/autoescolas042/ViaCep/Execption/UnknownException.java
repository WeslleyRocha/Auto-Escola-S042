package br.com.senai.s042.autoescolas042.ViaCep.Execption;

public class UnknownException extends RuntimeException {

    public UnknownException(String message) {
        super(message);
    }

    public UnknownException(String message, Throwable cause){
        super(message, cause);
    }
}
