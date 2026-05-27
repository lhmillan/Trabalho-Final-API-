package br.com.serratec.trabfinal_api.dto.request;

import br.com.serratec.trabfinal_api.enums.StatusOS;

import br.com.serratec.trabfinal_api.model.Cliente;
import br.com.serratec.trabfinal_api.model.Veiculo;
import jakarta.validation.constraints.NotNull;

public record OrdemServicoRequestDTO (

        @NotNull(message = "Cliente é obrigatório!")
        Cliente cliente,

        @NotNull(message = "Veículo é obrigatório!")
        Veiculo veiculo,

        @NotNull(message = "Status é obrigatório!")
        StatusOS status
) { }
