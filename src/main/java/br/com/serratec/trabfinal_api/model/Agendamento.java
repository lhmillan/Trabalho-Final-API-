package br.com.serratec.trabfinal_api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = " veiculo_id")
    private Veiculo veiculo;

    private LocalDateTime dataHora;

    public Agendamento() {}

    public void setId(Long id) {
        this.id = id;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public void setServico(Servico servico) {
        this.servico = servico;
    }
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Long getId() { return id; }
    public Cliente getCliente() {
        return cliente;
    }
    public Servico getServico() {
        return servico;
    }
    public Veiculo getVeiculo() {
        return veiculo;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }
}
