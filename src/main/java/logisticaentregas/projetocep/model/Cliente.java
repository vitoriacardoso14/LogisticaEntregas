package logisticaentregas.projetocep.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "tb_cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // nao pode ser nulo
    private String nome;

    @Column(nullable = false)
    private String email;

    private String telefone; // opcional

    @Column(nullable = false, unique = true) // obrigatorio e nao pode repetir
    private String cpf;

    // um cliente pode ter vários endereços
    // cascade: salva/deleta o cliente e também todos os endereços ligados a ele
    // fetch: define quando os endereços serão carregados (LAZY = apenas quando forem acessados)
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // evita loop infinito ao converter para json
    private List<Endereco> enderecos;
}