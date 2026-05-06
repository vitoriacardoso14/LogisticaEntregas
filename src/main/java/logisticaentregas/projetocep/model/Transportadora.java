package logisticaentregas.projetocep.model;

import jakarta.persistence;
import java.util.List;
import lombok.*;

import javax.annotation.processing.Generated;

@Entity
@Table(name = "Transportadora")
@Data
@NoArgsConstructor
@AllConstructor

public class Transportadora {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nome;
    private String cnpj;

    @OneToMany // uma transportadora pode ter varias entregas
    private List<Entrega> entregas;
}
