package br.com.serratec.trabfinal_api.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @Embedded
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente")
    private List<Veiculo> veiculos = new ArrayList<>();

    public Cliente() {}

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }
    public void setVeiculos(List<Veiculo> veiculos) { this.veiculos = veiculos; }

    public Long getId() { return id; }
    public String getNome() {return nome;}
    public String getEmail() {return email;}
    public String getTelefone() {return telefone;}
    public String getCpf() {return cpf;}
    public Endereco getEndereco() {return endereco;}
    public List<Veiculo> getVeiculos() {return veiculos;}
}