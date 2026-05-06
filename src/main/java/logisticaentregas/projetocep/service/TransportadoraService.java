package logisticaentregas.projetocep.service; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 

import logisticaentregas.projetocep.repository.TransportadoraRepository;
import logisticaentregas.projetocep.model.Transportadora; 

import java.util.List; 

@Service
public class TransportadoraService {

    @Autowired
    private TransportadoraRepository repository; // acesso ao banco

    // método para listar todas as transportadoras
    public List<Transportadora> listar() {
        return repository.findAll(); // retorna todas do banco
    }
}