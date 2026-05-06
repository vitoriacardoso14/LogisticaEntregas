package logisticaentregas.projetocep.model;

import jakarta.persistence;
import java.util.List;
import lombok.*;

import javax.annotation.processing.Generated;

@Entity
@Table(name = "Clientes")
@Data // gera getters, setters, toString 
@NoArgsConstructor // cria construtor vazio 
@AllConstructor // cria construtor com todos os atributos

public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // o banco gera o id automaticamente 

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @OneToMany(mappedBy = "cliente")  // um cliente pode ter varios endereços 1:N
    private List<Endereco> enderecos;
}
