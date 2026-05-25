package br.com.serratec.trabalhofinalapi.model;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome obrigatório")
    @Size (min =3, max= 100)
    private String nome;

    @NotBlank(message = "Telefone obrigatório")
    private String telefone;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email obrigatório" )
    private String email;
    
    @CPF(message = "CPF inválido")
    @NotBlank(message = "O CPF obrigatório")
    private String cpf;

    @NotBlank(message = "CEP é obrigatório")
    private String cep;

    @NotBlank(message = "Endereço obrigatório")
    private String endereco;



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    

}

