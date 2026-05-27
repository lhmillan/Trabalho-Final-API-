package br.com.serratec.trabfinal_api.dto;

import br.com.serratec.trabfinal_api.model.Endereco;

public record EnderecoResponseDTO(Long id, String cep, String logradouro, String numero, String bairro, String localidade,
        String uf) {
    public EnderecoResponseDTO(Endereco endereco) {
        this(
                endereco.getId(),
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getLocalidade(),
                endereco.getUf()
    );
    }
}
