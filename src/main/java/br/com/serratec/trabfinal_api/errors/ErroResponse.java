package br.com.serratec.trabfinal_api.errors;

import java.time.LocalDateTime;

public class ErroResponse {

    private LocalDateTime timestamp;
    private int status;
    private String mensagem;

    public ErroResponse(LocalDateTime timestamp, int status, String mensagem) {
        this.timestamp = timestamp;
        this.status = status;
        this.mensagem = mensagem;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }
}

