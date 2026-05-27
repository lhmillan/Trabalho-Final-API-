package br.com.serratec.trabfinal_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.trabfinal_api.dto.ClienteRequestDTO;
import br.com.serratec.trabfinal_api.dto.ClienteResponseDTO;
import br.com.serratec.trabfinal_api.dto.EnderecoResponseDTO;
import br.com.serratec.trabfinal_api.model.Cliente;
import br.com.serratec.trabfinal_api.model.Endereco;
import br.com.serratec.trabfinal_api.repository.ClienteRepository;
import br.com.serratec.trabfinal_api.repository.EnderecoRepository;

@Service

public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoService service;

    public List<ClienteResponseDTO> listar() {
        return clienteRepository.findAll().stream()
                .map(cliente -> new ClienteResponseDTO(cliente))
                .toList();
    }

    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);

        return new ClienteResponseDTO(cliente);
    }

    public ClienteResponseDTO cadastrar(ClienteRequestDTO dto) {
        
        EnderecoResponseDTO enderecoDTO = service.buscarCep(dto.endereco().getCep());  
        Endereco endereco = enderecoRepository.findById(enderecoDTO.id()).orElse(null);
        endereco.setNumero(dto.endereco().getNumero());
        endereco = enderecoRepository.save(endereco);

        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setTelefone(dto.telefone());
        cliente.setEmail(dto.email());
        cliente.setCpf(dto.cpf());
        cliente.setEndereco(endereco);

        cliente = clienteRepository.save(cliente);

        return new ClienteResponseDTO(cliente);
    }

}
