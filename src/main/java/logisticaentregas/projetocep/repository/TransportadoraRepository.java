package logisticaentregas.projetocep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import logisticaentregas.projetocep.model.Transportadora;

public interface TransportadoraRepository extends JpaRepository<Transportadora, Long> {
}