package br.com.serratec.trabfinal_api.dto.request;

import br.com.serratec.trabfinal_api.model.OS;
import br.com.serratec.trabfinal_api.model.Servico;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItemOSRequestDTO(

        @NotNull(message = "Ordem de Serviço é obrigatório!")
        OS ordemServico,

        @NotNull(message = "Serviço prestado é obrigatório!")
        Servico servico,

        Double desconto,

        @NotNull(message = "Quantidade de serviços a serem prestador é obrigatório!")
        @Min(value = 1, message = "Mínimo de quantidades é 1!")
        Integer quantidade

){ }
