package logisticaentregas.projetocep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import logisticaentregas.projetocep.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}