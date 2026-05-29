package br.com.serratec.trabfinal_api.service;

import br.com.serratec.trabfinal_api.dto.request.AgendamentoRequestDTO;
import br.com.serratec.trabfinal_api.dto.response.AgendamentoResponseDTO;
import br.com.serratec.trabfinal_api.model.Agendamento;
import br.com.serratec.trabfinal_api.model.Cliente;
import br.com.serratec.trabfinal_api.model.Servico;
import br.com.serratec.trabfinal_api.model.Veiculo;
import br.com.serratec.trabfinal_api.repository.AgendamentoRepository;
import br.com.serratec.trabfinal_api.repository.ClienteRepository;
import br.com.serratec.trabfinal_api.repository.ServicoRepository;
import br.com.serratec.trabfinal_api.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final VeiculoRepository veiculoRepository;
    private final ServicoRepository servicoRepository;

    public AgendamentoService(
            AgendamentoRepository agendamentoRepository,
            ClienteRepository clienteRepository,
            VeiculoRepository veiculoRepository,
            ServicoRepository servicoRepository ) {

        this.agendamentoRepository = agendamentoRepository;
        this.clienteRepository = clienteRepository;
        this.veiculoRepository = veiculoRepository;
        this.servicoRepository = servicoRepository;
    }

    private Agendamento converterParaEntity(
            AgendamentoRequestDTO dto,
            Cliente cliente,
            Veiculo veiculo,
            Servico servico ) {

        Agendamento agendamento = new Agendamento();

        agendamento.setCliente(cliente);
        agendamento.setVeiculo(veiculo);
        agendamento.setServico(servico);
        agendamento.setDataHora(dto.dataHora());

        return agendamento;
    }

    private AgendamentoResponseDTO converterParaDTO(Agendamento agendamento) {

        return new AgendamentoResponseDTO(
                agendamento.getId(),
                agendamento.getCliente(),
                agendamento.getVeiculo(),
                agendamento.getServico(),
                agendamento.getDataHora()
        );
    }

    private void validarClienteVeiculo(Cliente cliente, Veiculo veiculo) {
        if(!veiculo.getCliente().getId().equals(cliente.getId())) {
            throw new RuntimeException("Veículo não pertence ao cliente informado!");
        }
    }

    private void validarDisponibilidade(LocalDateTime dataHora) {
        Long quantidade = agendamentoRepository.countByDataHora(dataHora);

        if(quantidade >= 2) {
            throw new RuntimeException("Horário indisponível!");
        }
    }

    private void validarHorario(LocalDateTime horario) {

        int hora = horario.getHour();
        int minuto = horario.getMinute();

        if(minuto != 0) {
            throw new RuntimeException("Agendamentos dever ser realizados em horas exatas!");
        }

        if(hora != 9 && hora != 12 && hora != 15 && hora != 18) {
            throw new RuntimeException("Horário inválido! Os horários para agendamento são 09:00, 12:00 15:00 ou 18:00!");
        }
    }

    public List<AgendamentoResponseDTO> listar() {
        return agendamentoRepository.findAll().stream().map(this::converterParaDTO).toList();
    }

    public AgendamentoResponseDTO buscarPorId(Long id) {

        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Agendamento Não Encontrado!"));

        return converterParaDTO(agendamento);
    }

    public AgendamentoResponseDTO cadastrar(AgendamentoRequestDTO dto) {

        Cliente cliente = clienteRepository.findById(dto.cliente().getId()).orElseThrow(() ->
                new RuntimeException("Cliente não encontrado!"));

        Veiculo veiculo = veiculoRepository.findById(dto.veiculo().getId()).orElseThrow(() ->
                new RuntimeException("Veículo não encontrado!"));

        Servico servico = servicoRepository.findById(dto.servico().getId()).orElseThrow(() ->
                new RuntimeException("Serviço não encontrado!"));

        validarClienteVeiculo(cliente, veiculo);
        validarDisponibilidade(dto.dataHora());
        validarHorario(dto.dataHora());

        Agendamento agendamento = converterParaEntity(dto, cliente, veiculo, servico);
        agendamento = agendamentoRepository.save(agendamento);

        return converterParaDTO(agendamento);
    }

    public AgendamentoResponseDTO atualizar(Long id, AgendamentoRequestDTO dto) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Agendamento não encontrado!"));

        Cliente cliente = clienteRepository.findById(dto.cliente().getId()).orElseThrow(() ->
                new RuntimeException("Cliente não encontrado!"));

        Veiculo veiculo = veiculoRepository.findById(dto.veiculo().getId()).orElseThrow(() ->
                new RuntimeException("Veículo não encontrado!"));

        Servico servico = servicoRepository.findById(dto.servico().getId()).orElseThrow(() ->
                new RuntimeException("Serviço não encontrado!"));

        validarClienteVeiculo(cliente, veiculo);
        validarDisponibilidade(dto.dataHora());
        validarHorario(dto.dataHora());

        agendamento.setCliente(cliente);
        agendamento.setVeiculo(veiculo);
        agendamento.setServico(servico);
        agendamento.setDataHora(dto.dataHora());

        agendamento = agendamentoRepository.save(agendamento);

        return converterParaDTO(agendamento);
    }

    public void remover(Long id) {

        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Agendamento não encontrado!"));

        agendamentoRepository.delete(agendamento);
    }
}
