package br.com.serratec.trabfinal_api.model;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import br.com.serratec.trabfinal_api.model.OS;
import br.com.serratec.trabfinal_api.model.Servico;

@Entity
@Table(name = "item_os")
public class ItemOS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ordem_servico_id")
    private OS ordemServico;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

    private Double valor;
    private Double desconto;
    private Integer quantidade;
    private Double subtotal;

    public ItemOS() {}

    public void setId(Long id) { this.id = id; }
    public void setOrdemServico(OS ordemServico) {  this.ordemServico = ordemServico; }
    public void setValor(Double valor) { this.valor = valor; }
    public void setDesconto(Double desconto) { this.desconto = desconto; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
    public void setServico(Servico servico) { this.servico = servico; }

    public Long getId() { return id; }
    public OS getOrdemServico() { return ordemServico; }
    public Double getValor() { return valor; }
    public Double getDesconto() { return desconto; }
    public Integer getQuantidade() { return quantidade; }
    public Double getSubtotal() { return (valor - desconto) * quantidade; }
    public Servico getServico() { return servico; }

}

