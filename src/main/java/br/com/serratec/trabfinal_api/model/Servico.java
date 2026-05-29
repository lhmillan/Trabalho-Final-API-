package br.com.serratec.trabfinal_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "servico")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Double valor;
    private String tempoEstimado;
    
    @ManyToOne
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;

    public Servico() {}

    public Long getId() { return id; }
    public String getDescricao() { return this.descricao; }
    public Double getValor() { return this.valor; }
    public String getTempoEstimado() { return this.tempoEstimado; }

    public void setId(Long id) { this.id = id; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setValor(Double valor) { this.valor = valor; }
    public void setTempoEstimado(String tempo) { this.tempoEstimado = tempo; }
	public Veiculo getVeiculo() { return veiculo; }
	public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }
    
}
