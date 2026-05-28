package br.com.serratec.trabfinal_api.dto.request;

import br.com.serratec.trabfinal_api.dto.response.ClienteResponseDTO;
import br.com.serratec.trabfinal_api.model.Cliente;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record VeiculoRequestDTO (

        @NotBlank(message = "Placa do carro é obrigatória!")
        @Size(min = 7, max = 7, message = "Placa deve conter 7 caracteres!")
        String placa,

        @NotBlank(message = "Marca do carro é Obrigatória!")
        String marca,

        @NotBlank(message = "Modelo do carro é Obrigatório!")
        String modelo,

        @NotNull(message = "Ano do carro é Obrigatório!")
        @Min(value = 1950, message = "Ano inválido!")
        String ano,

        @NotBlank(message = "Cor do carro é obrigatório!")
        String cor,

        @NotBlank(message = "Obrigatório definir o dono do veículo!")
        ClienteResponseDTO cliente
){}
