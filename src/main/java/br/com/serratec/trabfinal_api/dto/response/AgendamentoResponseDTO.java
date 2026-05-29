package br.com.serratec.trabfinal_api.dto.response;

import br.com.serratec.trabfinal_api.model.Cliente;
import br.com.serratec.trabfinal_api.model.Servico;
import br.com.serratec.trabfinal_api.model.Veiculo;

import java.time.LocalDateTime;


public record AgendamentoResponseDTO(

        Long id,
        Cliente cliente,
        Veiculo veiculo,
        Servico servico,
        LocalDateTime dataHora
) {}
