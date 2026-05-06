package logisticaentregas.projetocep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import logisticaentregas.projetocep.model.Cliente;
import java.util.Optional;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // busca cliente pelo CPF
    Optional<Cliente> findByCpf(String cpf);

    // busca clientes por nome
    List<Cliente> findByNomeContainingIgnoreCase(String nome);
}