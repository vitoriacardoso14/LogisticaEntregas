package logisticaentregas.projetocep.service; 

import lombok.Data; 

@Data // gera getters, setters e toString automaticamente
public class ViaCepResponse { // classe que representa o retorno da API ViaCEP

    private String logradouro; // rua retornada pela API
    private String localidade; // cidade retornada
    private String uf; // estado retornado
}