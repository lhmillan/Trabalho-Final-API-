package br.com.serratec.trabfinal_api.errors;


public class RegistroNaoEncontradoException extends RuntimeException {

    public RegistroNaoEncontradoException(String message) {
        super(message);
    }
}