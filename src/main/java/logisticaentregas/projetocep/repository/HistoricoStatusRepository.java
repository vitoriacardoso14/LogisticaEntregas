package logisticaentregas.projetocep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import logisticaentregas.projetocep.model.HistoricoStatus;
import logisticaentregas.projetocep.model.Entrega;
import java.util.List;

public interface HistoricoStatusRepository extends JpaRepository<HistoricoStatus, Long> {

    // buscar histórico de status de uma entrega
    List<HistoricoStatus> findByEntrega(Entrega entrega);
}