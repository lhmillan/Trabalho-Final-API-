package br.com.serratec.trabfinal_api.dto.response;

import br.com.serratec.trabfinal_api.model.OS;
import br.com.serratec.trabfinal_api.model.Servico;

public record ItemOSResponseDTO (

        Long id,
        OS ordemServico,
        Servico servico,
        Double valor,
        Double desconto,
        Integer quantidade,
        Double subtotal
){}
