package br.com.serratec.trabfinal_api.service;

import br.com.serratec.trabfinal_api.dto.request.ClienteRequestDTO;
import br.com.serratec.trabfinal_api.dto.response.ClienteResponseDTO;
import br.com.serratec.trabfinal_api.model.Cliente;
import br.com.serratec.trabfinal_api.repository.ClienteRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    private ClienteResponseDTO converterParaDTO(Cliente cliente) {

        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getCpf(),
                cliente.getEndereco()
        );
    }

    private void atualizarDadosCliente(Cliente cliente, ClienteRequestDTO dto) {

        cliente.setNome(dto.nome());
        cliente.setTelefone(dto.telefone());
        cliente.setEmail(dto.email());
        cliente.setCpf(dto.cpf());
        cliente.setEndereco(dto.endereco());
    }

    public List<ClienteResponseDTO> listarClientes() {

        return clienteRepository.findAll().stream().map(this::converterParaDTO).toList();
    }

    public ClienteResponseDTO buscarPorId(Long id) {

        Cliente cliente = clienteRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("Cliente não encontrado!"));

        return converterParaDTO(cliente);
    }

    public ClienteResponseDTO cadastrarCliente(ClienteRequestDTO dto) {

        Cliente cliente = new Cliente();
        atualizarDadosCliente(cliente, dto);
        cliente = clienteRepository.save(cliente);

        return converterParaDTO(cliente);
    }

    public ClienteResponseDTO atualizarCliente(Long id, ClienteRequestDTO dto) {

        Cliente cliente = clienteRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("Cliente não encontrado!"));

        atualizarDadosCliente(cliente, dto);
        cliente = clienteRepository.save(cliente);

        return converterParaDTO(cliente);
    }

    public void removerCliente(Long id) {

        Cliente cliente = clienteRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("Cliente não encontrado!"));

        clienteRepository.delete(cliente);
    }
}