package br.com.serratec.trabfinal_api.service;

import br.com.serratec.trabfinal_api.configuration.MailConfig;
import br.com.serratec.trabfinal_api.dto.request.ClienteRequestDTO;
import br.com.serratec.trabfinal_api.dto.response.ClienteResponseDTO;
import br.com.serratec.trabfinal_api.dto.response.EnderecoResponseDTO;
import br.com.serratec.trabfinal_api.model.Cliente;
import br.com.serratec.trabfinal_api.model.Endereco;
import br.com.serratec.trabfinal_api.repository.ClienteRepository;
import br.com.serratec.trabfinal_api.repository.EnderecoRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private MailConfig mailConfig;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoService service;

    @Autowired
    private EnderecoRepository enderecoRepository;

    

    private ClienteResponseDTO converterParaDTO(Cliente cliente) {

        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getCpf(),
                cliente.getEndereco());
    }

    private void atualizarDadosCliente(Cliente cliente, ClienteRequestDTO dto) {
        EnderecoResponseDTO enderecoDTO = service.buscarCep(dto.endereco().cep());
        Endereco endereco = enderecoRepository.findById(enderecoDTO.id()).orElse(null);
        endereco.setNumero(dto.endereco().numero());

        cliente.setEndereco(endereco);
        endereco = enderecoRepository.save(endereco);
        cliente.setNome(dto.nome());
        cliente.setTelefone(dto.telefone());
        cliente.setEmail(dto.email());
        cliente.setCpf(dto.cpf());
        cliente.setEndereco(endereco);
    }

    public List<ClienteResponseDTO> listarClientes() {

        return clienteRepository.findAll().stream().map(this::converterParaDTO).toList();
    }

    public ClienteResponseDTO buscarPorId(Long id) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        return converterParaDTO(cliente);
    }
 
    @Transactional
    public ClienteResponseDTO cadastrarCliente(ClienteRequestDTO dto) {

        Cliente cliente = new Cliente();

        EnderecoResponseDTO enderecoDTO = service.buscarCep(dto.endereco().cep());
        Endereco endereco = enderecoRepository.findById(enderecoDTO.id()).orElse(null);
        endereco.setNumero(dto.endereco().numero());

        cliente.setEndereco(endereco);
        endereco = enderecoRepository.save(endereco);
        atualizarDadosCliente(cliente, dto);

        cliente = clienteRepository.save(cliente);

        String txtEmail = "Parabéns, " + cliente.getNome() + "! Seguem abaixo os dados do seu cadastro:\n" +
                "Email: " + cliente.getEmail() + "\nTelefone: " + cliente.getTelefone() + "\nEndereço: "
                + cliente.getEndereco();
        mailConfig.sendEmail(dto.email(), "Cadastro realizado na Oficina!", txtEmail);

        return converterParaDTO(cliente);
    }

    public ClienteResponseDTO atualizarCliente(Long id, ClienteRequestDTO dto) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        // pegando o cep e o número do Response DTO e convertendo para o
        // campo endereço de cliente

        EnderecoResponseDTO enderecoDTO = service.buscarCep(dto.endereco().cep());
        Endereco endereco = enderecoRepository.findById(enderecoDTO.id()).orElse(null);
        endereco.setNumero(dto.endereco().numero());
        atualizarDadosCliente(cliente, dto);

        endereco = enderecoRepository.save(endereco);
        cliente.setEndereco(endereco);
        cliente = clienteRepository.save(cliente);

        String txtEmail = "Atenção, " + cliente.getNome() + "! Seus dados de cadastro foram alterados!";
        mailConfig.sendEmail(dto.email(), "Cadastro realizado na Oficina!", txtEmail);
        return converterParaDTO(cliente);
    }

    public void removerCliente(Long id) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        clienteRepository.delete(cliente);
    }
}
