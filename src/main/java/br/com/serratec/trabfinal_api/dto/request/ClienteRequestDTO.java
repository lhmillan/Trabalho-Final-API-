package br.com.serratec.trabfinal_api.dto.request;

import br.com.serratec.trabfinal_api.model.Endereco;
import jakarta.validation.constraints.*;

public record ClienteRequestDTO(

        @NotBlank(message = "Nome é obrigatório!")
        @Size(min = 3, max = 100, message = "Número de caracteres não deve ser maior que 100 ou menor que 3")
        String nome,

        String telefone,

        @NotBlank(message = "CPF é obrigatório!")
        @Pattern(regexp = "\\d{11}", message = "Insira somente os números!")
        String cpf,

        @Email(message = "Email inválido!")
        String email,

        @NotNull(message = "Endereço é obrigatório!")
        Endereco endereco
){
}
