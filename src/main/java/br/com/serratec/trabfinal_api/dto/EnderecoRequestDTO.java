package br.com.serratec.trabfinal_api.dto;

import br.com.serratec.trabfinal_api.model.Endereco;

public record EnderecoRequestDTO(
    String cep, 
    String numero) {
}

