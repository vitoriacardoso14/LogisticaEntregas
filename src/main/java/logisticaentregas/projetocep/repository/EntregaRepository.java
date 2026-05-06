package logisticaentregas.projetocep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import logisticaentregas.projetocep.model.Entrega;
import logisticaentregas.projetocep.model.Cliente;
import java.util.List;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {

    // buscar entregas por cliente (usado no rastreamento por CPF)
    List<Entrega> findByDestinatario(Cliente cliente);
}