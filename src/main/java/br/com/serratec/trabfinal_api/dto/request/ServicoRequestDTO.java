package br.com.serratec.trabfinal_api.dto.request;

import br.com.serratec.trabfinal_api.model.PecaServico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ServicoRequestDTO (

        @NotBlank(message = "Tipo de Serviço não informado!")
        String descricao,

        @NotNull(message = "Valor é obrigatório!")
        @Positive(message = "Valor deve ser maior do que zero!")
        Double valor,

        @NotBlank(message = "Tempo estimado é obrigatório!")
        String tempoEstimado,

        PecaServico pecasUsadas
){ }
