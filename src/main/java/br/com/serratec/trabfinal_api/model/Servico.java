package br.com.serratec.trabfinal_api.model;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "servico")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Double valor;
    private String tempoEstimado;

    @OneToMany(mappedBy = "servico", cascade = CascadeType.ALL)
    private List<PecaServico> pecasUsadas;

    public Servico() {
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public Double getValor() {
        return this.valor;
    }

    public String getTempoEstimado() {
        return this.tempoEstimado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setTempoEstimado(String tempo) {
        this.tempoEstimado = tempo;
    }

    public List<PecaServico> getPecasUsada() {
        return pecasUsadas;
    }

    public void setPecasUsada(List<PecaServico> pecasUsada) {
        this.pecasUsadas = pecasUsada;
    }
}
