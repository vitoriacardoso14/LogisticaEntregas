package logisticaentregas.projetocep.model;

import jakarta.persistence;
import lombok.*;

import javax.annotation.processing.Generated;

@Entity
@Table(name = "Endereços")
@Data
@NoArgsConstructor
@AllConstructor

public class Endereco {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String cep;
    private String rua;
    private String cidade;
    private String estado;

    @ManyToOne // muitos enderecos pertecem a um cliente
    @JoinColumn(name = cliente_id) // cria a coluna cliente_id como chave estrangeira 
    private Cliente cliente; // cliente dono do endereço
}
