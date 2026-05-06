package logisticaentregas.projetocep.model;

import jakarta.persistence;
import java.util.List;
import lombok.*;
import java.time.LocalDate;

import javax.annotation.processing.Generated;

@Entity
@Table(name = "Entregas")
@Data
@NoArgsConstructor
@AllConstructor

public class Entrega {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private LocalDate dataEnvio, dataEntrega;
    private String statusAtual;

    @ManyToOne // muitas entregas podem pertencer a um cliente
    @JoinColumn(name = "cliente_id")
    private Cliente cliente; // cliente que recebe a entrega

    @ManyToOne // muitas entregas podem usar uma transportadora
    @JoinColumn(name = "transportadora_id") 
    private Transportadora transportadora; // transportadora responsável

    @ManyToOne // muitas entregas podem ter um endereço
    @JoinColumn(name = "endereco_id") 
    private Endereco endereco; // endereço de entrega

    @OneToMany(mappedBy = "entrega") // uma entrega pode ter vários status ao longo do tempo
    private List<HistoricoStatus> historico; 
}