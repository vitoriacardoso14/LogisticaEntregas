package logisticaentregas.projetocep.model; 

import jakarta.persistence.*;
import lombok.*; // Lombok
import java.time.LocalDate;

@Entity
@Table(name = "tb_historico_status")
@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor
public class HistoricoStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(nullable = false) // obrigatório
    private String status; // descrição do status

    @Column(nullable = false) // obrigatório
    private LocalDate data; // data do status

    // muitos registros pertencem a uma entrega
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entrega_id", nullable = false) // chave estrangeira
    private Entrega entrega; // entrega associada
}