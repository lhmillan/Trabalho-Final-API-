package br.com.serratec.trabfinal_api.errors;

public class BancoDeDadosException extends RuntimeException {

    public BancoDeDadosException(String message) {
        super(message);
    }
}