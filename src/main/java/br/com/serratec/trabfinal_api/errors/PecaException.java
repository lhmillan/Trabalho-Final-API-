package br.com.serratec.trabfinal_api.errors;

public class PecaException extends RuntimeException {
    public PecaException(String mensagem) {
        super(mensagem);
    }
}
