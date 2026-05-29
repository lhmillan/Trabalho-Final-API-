package br.com.serratec.trabfinal_api.dto.request;

import org.hibernate.annotations.processing.Pattern;

import br.com.serratec.trabfinal_api.dto.response.EnderecoResponseDTO;
import br.com.serratec.trabfinal_api.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.annotation.Generated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.Size;

public record ClienteRequestDTO(

    @NotBlank(message = "Nome é obrigatório!")
    @Size(min = 3, max = 100, message = "Número de caracteres não deve ser maior que 100 ou menor que 3")
    String nome,

    @NotBlank(message = "Telefone é obrigatório!")
    String telefone,

    @NotBlank(message = "CPF é obrigatório!")
    @Size(min = 11, max = 11, message = "O cpf deve conter exatamente 11 digitos!")
    String cpf,

    @Size(message = "Email inválido!")
    String email,

    @Valid
    EnderecoResponseDTO endereco

    ){
}