package br.com.serratec.trabfinal_api.dto.response;

public record VeiculoResponseDTO (

        Long id,
        Long clienteId,
        String placa,
        String marca,
        String modelo,
        String cor,
        String ano,
        String url
){
}
