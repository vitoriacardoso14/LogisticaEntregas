package logisticaentregas.projetocep.model; // pacote

import jakarta.persistence.*; 
import lombok.*; 
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_entrega")
@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataEnvio;

    @Column(nullable = false)
    private String statusAtual; 

    // muitas entregas pertencem a um cliente (destinatário)
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "cliente_id", nullable = false) // chave estrangeira
    private Cliente destinatario; // cliente que recebe a entrega

    // muitas entregas usam uma transportadora
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "transportadora_id", nullable = false) // chave estrangeira
    private Transportadora transportadora; // transportadora responsável

    // muitas entregas podem ter um endereço
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "endereco_id", nullable = false) // chave estrangeira
    private Endereco endereco; // endereço da entrega

    // uma entrega tem vários registros de status
    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
    private List<HistoricoStatus> historico; // histórico de status
}