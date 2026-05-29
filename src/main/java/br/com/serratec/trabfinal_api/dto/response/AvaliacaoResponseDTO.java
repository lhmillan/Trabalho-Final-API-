package br.com.serratec.trabfinal_api.dto.response;

public class AvaliacaoResponseDTO {

    private Long id;
    private int nota;
    private String comentario;
    private Long clienteId;
    private Long servicoId;

    public AvaliacaoResponseDTO() {}

    public AvaliacaoResponseDTO(Long id, int nota, String comentario, Long clienteId, Long servicoId) {
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
        this.clienteId = clienteId;
        this.servicoId = servicoId;
    }

    public Long getId() {
        return id;
    }

    public int getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public Long getServicoId() {
        return servicoId;
    }
}