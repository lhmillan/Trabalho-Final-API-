package br.com.serratec.trabfinal_api.dto.response;

import br.com.serratec.trabfinal_api.model.Endereco;

public record ClienteResponseDTO(

        Long id,
        String nome,
        String cpf,
        String telefone,
        String email,
        Endereco endereco
){ }
