package br.com.serratec.trabfinal_api.dto.response;

import br.com.serratec.trabfinal_api.enums.StatusOS;
import br.com.serratec.trabfinal_api.model.Cliente;
import br.com.serratec.trabfinal_api.model.Veiculo;

public record OrdemServicoResponseDTO (

        Long id,
        Cliente cliente,
        Veiculo veiculo,
        StatusOS status
){}
