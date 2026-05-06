package logisticaentregas.projetocep.model; 

import jakarta.persistence.*; 
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore; // evita loop JSON
import java.util.List;

@Entity 
@Table(name = "tb_transportadora") 
@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor
public class Transportadora {

    @Id // chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // obrigatório
    private String nome; //

    @Column(nullable = false, unique = true) // obrigatório e único
    private String cnpj;

    // uma transportadora pode ter várias entregas
    @OneToMany(mappedBy = "transportadora", fetch = FetchType.LAZY) 
    @JsonIgnore 
    private List<Entrega> entregas;
}