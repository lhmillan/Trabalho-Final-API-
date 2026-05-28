package br.com.serratec.trabfinal_api.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Pecas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotBlank(message = "Preencha o nome da peça!")
    String nomePeça;

    @NotBlank(message = "Preencha a quantidade!")
    String quantidade;

    String preco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomePeça() {
        return nomePeça;
    }

    public void setNomePeça(String nomePeça) {
        this.nomePeça = nomePeça;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

}
