package br.com.serratec.trabfinal_api.dto.response;

public record VeiculoResponseDTO (

        Long id,
        String placa,
        String marca,
        String modelo,
        String cor,
        String ano,
        ClienteResponseDTO cliente,
        String uri
){
}
