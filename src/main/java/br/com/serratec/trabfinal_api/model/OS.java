package br.com.serratec.trabfinal_api.model;

import br.com.serratec.trabfinal_api.enums.StatusOS;
import jakarta.annotation.Generated;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ordem_servico")
public class OS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @Enumerated(EnumType.STRING)
    private StatusOS status;

    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL)
    private List<ItemOS> itens;

    public OS() {}

    public void setId(Long id) { this.id = id; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }
    public void setStatus(StatusOS status) { this.status = status; }
    public void setItens(List<ItemOS> itens) { this.itens = itens; }

    public Long getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Veiculo getVeiculo() { return veiculo; }
    public StatusOS getStatus() { return status; }
    public List<ItemOS> getItens() { return itens; }

}
