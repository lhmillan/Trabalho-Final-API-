package br.com.serratec.trabfinal_api.errors;

public class CepInvalidoException extends RuntimeException {

    public CepInvalidoException(String message) {
        super(message);
    }
}