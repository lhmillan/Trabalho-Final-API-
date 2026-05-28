package br.com.serratec.trabfinal_api.dto.response;

import br.com.serratec.trabfinal_api.model.Cliente;
import br.com.serratec.trabfinal_api.model.Endereco;


public record ClienteResponseDTO(Long id,
                String nome,
                String telefone,
                String cpf,
                String email,
                Endereco endereco) {
       
}