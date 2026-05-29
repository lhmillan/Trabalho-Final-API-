package br.com.serratec.trabfinal_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "avaliacoes")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private int nota;

    private String comentario;

    @Column(name = "id_cliente")
    private Long clienteId;

    @Column(name = "id_servico")
    private Long servicoId;

    public Avaliacao() {}

    public Avaliacao(int nota, String comentario, Long clienteId, Long servicoId) {
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

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getServicoId() {
        return servicoId;
    }

    public void setServicoId(Long servicoId) {
        this.servicoId = servicoId;
    }
}