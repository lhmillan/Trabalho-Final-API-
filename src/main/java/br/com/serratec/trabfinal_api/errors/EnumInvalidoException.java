package br.com.serratec.trabfinal_api.errors;

public class EnumInvalidoException extends RuntimeException {

    public EnumInvalidoException(String mensagem) {
        super(mensagem);
    }
}