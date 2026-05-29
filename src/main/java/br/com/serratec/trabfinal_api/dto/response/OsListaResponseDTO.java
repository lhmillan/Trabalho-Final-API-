package br.com.serratec.trabfinal_api.dto.response;

import java.util.List;

public record OsListaResponseDTO(
        Long numeroOS,
        String cliente,
        String veiculo,
        List<String> servicos,
        Double valorTotal

) { }