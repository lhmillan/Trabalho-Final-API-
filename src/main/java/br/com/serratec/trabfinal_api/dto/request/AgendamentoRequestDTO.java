package br.com.serratec.trabfinal_api.dto.request;

import java.time.LocalDateTime;

import br.com.serratec.trabfinal_api.model.Cliente;
import br.com.serratec.trabfinal_api.model.Servico;
import br.com.serratec.trabfinal_api.model.Veiculo;
import jakarta.validation.constraints.NotNull;

public record AgendamentoRequestDTO(

        @NotNull
        Cliente cliente,

        @NotNull
        Veiculo veiculo,

        @NotNull
        Servico servico,

        @NotNull
        LocalDateTime dataHora
) {}
