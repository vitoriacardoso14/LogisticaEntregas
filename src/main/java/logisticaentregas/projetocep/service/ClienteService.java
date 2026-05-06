package logisticaentregas.projetocep.service;

import org.springframework.beans.factory.annotation.Autowired; // injecao automatica de dependencias 
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import logisticaentregas.projetocep.model.Cliente;
import logisticaentregas.projetocep.model.Endereco;
import logisticaentregas.projetocep.repository.ClienteRepository;
import logisticaentregas.projetocep.repository.EnderecoRepository;

import java.util.List;

@Service // indica que essa classe contém regras de negócio
public class ClienteService {

    @Autowired // injeta automaticamente o repository
    private ClienteRepository clienteRepository; // permite acessar o banco de clientes

    @Autowired
    private EnderecoRepository enderecoRepository; // permite acessar o banco de endereços

    private final String VIA_CEP_URL = "https://viacep.com.br/ws/";

    // cadastrar cliente e buscar endereço pelo CEP
    public Cliente cadastrarClienteComEndereco(Cliente cliente, String cep) {

        RestTemplate restTemplate = new RestTemplate(); // cria objeto para fazer requisições HTTP

        String url = VIA_CEP_URL + cep + "/json/"; // monta a URL completa com o CEP

        // faz requisição GET na API e converte a resposta para ViaCepResponse
        ViaCepResponse response = restTemplate.getForObject(url, ViaCepResponse.class);

        Endereco endereco = new Endereco(); // cria novo objeto Endereco

        endereco.setCep(cep); // define o CEP digitado
        endereco.setRua(response.getLogradouro()); // preenche rua com dados da API
        endereco.setCidade(response.getLocalidade()); // preenche cidade 
        endereco.setEstado(response.getUf()); // preenche estado
        endereco.setCliente(cliente); // associa o endereço ao cliente (ainda não salvo)

        // salva cliente no banco 
        Cliente clienteSalvo = clienteRepository.save(cliente);

        endereco.setCliente(clienteSalvo); // garante associação com cliente persistido
        enderecoRepository.save(endereco); // salva o endereço no banco

        return clienteSalvo;
    }

    // método para listar todos os clientes
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll(); // retorna todos os registros da tabela cliente
    }

    // método para buscar cliente pelo CPF
    public Cliente buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf).orElse(null); // retorna cliente ou null se não encontrar
    }
}