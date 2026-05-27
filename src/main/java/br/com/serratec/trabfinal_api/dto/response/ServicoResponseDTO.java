package br.com.serratec.trabfinal_api.dto.response;

public record ServicoResponseDTO (

        Long id,
        String descricao,
        Double valor,
        String tempoEstimado
){}
