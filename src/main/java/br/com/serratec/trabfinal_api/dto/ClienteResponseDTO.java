package br.com.serratec.trabfinal_api.dto;

import br.com.serratec.trabfinal_api.model.Cliente;
import br.com.serratec.trabfinal_api.model.Endereco;


public record ClienteResponseDTO(Long id,
                String nome,
                String cpf,
                String telefone,
                String email,
                Endereco endereco) {
        public ClienteResponseDTO(Cliente cliente) {
                this(
                                cliente.getId(),
                                cliente.getNome(),
                                cliente.getCpf(),
                                cliente.getEmail(),
                                cliente.getTelefone(),
                                cliente.getEndereco());
        }
}