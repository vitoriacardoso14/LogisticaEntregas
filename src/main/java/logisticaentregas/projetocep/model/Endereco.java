package logisticaentregas.projetocep.model; // pacote

import jakarta.persistence.*; 
import lombok.*; // Lombok

@Entity 
@Table(name = "tb_endereco") 
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
public class Endereco { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; 

    @Column(nullable = false) // obrigatório
    private String cep; 

    private String rua; 
    private String cidade; 
    private String estado; 

    // muitos endereços pertencem a um cliente
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "cliente_id", nullable = false) // chave estrangeira no banco
    private Cliente cliente; // cliente dono do endereço
}