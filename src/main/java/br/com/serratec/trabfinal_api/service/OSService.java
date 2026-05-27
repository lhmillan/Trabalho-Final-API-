package br.com.serratec.trabfinal_api.service;

import br.com.serratec.trabfinal_api.dto.request.OrdemServicoRequestDTO;
import br.com.serratec.trabfinal_api.dto.response.OrdemServicoResponseDTO;
import br.com.serratec.trabfinal_api.model.Cliente;
import br.com.serratec.trabfinal_api.model.OS;
import br.com.serratec.trabfinal_api.model.Veiculo;
import br.com.serratec.trabfinal_api.repository.ClienteRepository;
import br.com.serratec.trabfinal_api.repository.OSRepository;
import br.com.serratec.trabfinal_api.repository.VeiculoRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OSService {

    private final OSRepository osRepository;
    private final ClienteRepository clienteRepository;
    private final VeiculoRepository veiculoRepository;

    public OSService(OSRepository osRepository, ClienteRepository clienteRepository, VeiculoRepository veiculoRepository) {

        this.osRepository = osRepository;
        this.clienteRepository = clienteRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public List<OrdemServicoResponseDTO> listarOS() {

        return osRepository.findAll().stream().map(this::converterParaDTO).toList();
    }

    public OrdemServicoResponseDTO buscarPorId(Long id) {

        OS os = osRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("Ordem de serviço não encontrada!"));

        return converterParaDTO(os);
    }

    public OrdemServicoResponseDTO cadastrarOS(OrdemServicoRequestDTO dto) {

        Cliente cliente = clienteRepository.findById(dto.cliente().getId()).orElseThrow(() ->
                        new RuntimeException("Cliente não encontrado!"));

        Veiculo veiculo = veiculoRepository.findById(dto.veiculo().getId()).orElseThrow(() ->
                        new RuntimeException("Veículo não encontrado!"));

        validarClienteDoVeiculo(cliente, veiculo);
        OS os = new OS();
        atualizarDadosOS(os, dto, cliente, veiculo);
        os = osRepository.save(os);

        return converterParaDTO(os);
    }

    public OrdemServicoResponseDTO atualizarOS(Long id, OrdemServicoRequestDTO dto) {

        OS os = osRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("OS não encontrada!"));

        Cliente cliente = clienteRepository.findById(dto.cliente().getId()).orElseThrow(() ->
                        new RuntimeException("Cliente não encontrado!"));

        Veiculo veiculo = veiculoRepository.findById(dto.veiculo().getId()).orElseThrow(() ->
                        new RuntimeException("Veículo não encontrado!"));

        validarClienteDoVeiculo(cliente, veiculo);
        atualizarDadosOS(os, dto, cliente, veiculo);
        os = osRepository.save(os);

        return converterParaDTO(os);
    }

    public void removerOS(Long id) {

        OS os = osRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("OS não encontrada!"));

        osRepository.delete(os);
    }

    private void atualizarDadosOS(OS os, OrdemServicoRequestDTO dto, Cliente cliente, Veiculo veiculo) {

        os.setCliente(cliente);
        os.setVeiculo(veiculo);
        os.setStatus(dto.status());
    }

    private void validarClienteDoVeiculo(Cliente cliente, Veiculo veiculo) {

        if (!veiculo.getCliente().getId().equals(cliente.getId())) {
            throw new RuntimeException(
                    "O veículo não pertence ao cliente informado!");
        }
    }

    private OrdemServicoResponseDTO converterParaDTO(OS os) {

        return new OrdemServicoResponseDTO(
                os.getId(),
                os.getCliente(),
                os.getVeiculo(),
                os.getStatus()
        );
    }
}