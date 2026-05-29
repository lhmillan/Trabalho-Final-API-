package br.com.serratec.trabfinal_api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record EnderecoRequestDTO(
    @NotBlank(message = "O CEP deve ser preenchido!") String cep, 
    String numero) {
}

