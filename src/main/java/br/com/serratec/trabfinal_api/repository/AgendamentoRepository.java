package br.com.serratec.trabfinal_api.repository;

import br.com.serratec.trabfinal_api.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    Long countByDataHora(LocalDateTime dataHora);
}
