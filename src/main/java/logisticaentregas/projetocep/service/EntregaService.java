package logisticaentregas.projetocep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 

import logisticaentregas.projetocep.model.*; 
import logisticaentregas.projetocep.repository.*; 

import java.time.LocalDate; 
import java.util.List; 

@Service 
public class EntregaService { 

    @Autowired
    private EntregaRepository entregaRepository; // acesso ao banco de entregas

    @Autowired
    private ClienteRepository clienteRepository; // acesso ao banco de clientes

    @Autowired
    private EnderecoRepository enderecoRepository; // acesso ao banco de endereços

    @Autowired
    private TransportadoraRepository transportadoraRepository; // acesso ao banco de transportadoras

    @Autowired
    private HistoricoStatusRepository historicoRepository; // acesso ao histórico de status

    // método responsável por criar uma nova entrega
    public Entrega criarEntrega(Long clienteId, Long enderecoId, Long transportadoraId) {

        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(); // busca cliente pelo ID
        Endereco endereco = enderecoRepository.findById(enderecoId).orElseThrow(); // busca endereço pelo ID
        Transportadora transportadora = transportadoraRepository.findById(transportadoraId).orElseThrow(); // busca transportadora

        Entrega entrega = new Entrega(); // cria nova entrega

        entrega.setDestinatario(cliente); // define cliente que receberá a entrega
        entrega.setEndereco(endereco); // define endereço de entrega
        entrega.setTransportadora(transportadora); // define transportadora responsável
        entrega.setDataEnvio(LocalDate.now()); // define data atual como envio
        entrega.setStatusAtual("Preparando envio"); // define status inicial

        Entrega entregaSalva = entregaRepository.save(entrega); // salva a entrega no banco

        HistoricoStatus historico = new HistoricoStatus(); // cria registro de histórico

        historico.setEntrega(entregaSalva); // associa ao objeto entrega
        historico.setStatus("Preparando envio"); // status inicial
        historico.setData(LocalDate.now()); // data atual

        historicoRepository.save(historico); // salva histórico no banco

        return entregaSalva; // retorna entrega criada
    }

    // método para atualizar status da entrega
    public void atualizarStatus(Long entregaId, String novoStatus) {

        Entrega entrega = entregaRepository.findById(entregaId).orElseThrow(); // busca entrega

        entrega.setStatusAtual(novoStatus); // atualiza status atual
        entregaRepository.save(entrega); // salva alteração no banco

        HistoricoStatus historico = new HistoricoStatus(); // cria novo registro de histórico

        historico.setEntrega(entrega); // associa à entrega
        historico.setStatus(novoStatus); // define novo status
        historico.setData(LocalDate.now()); // define data atual

        historicoRepository.save(historico); // salva histórico
    }

    // método para listar todas as entregas
    public List<Entrega> listarEntregas() {
        return entregaRepository.findAll(); // retorna todas as entregas
    }

    // método para buscar entrega por ID
    public Entrega buscarPorId(Long id) {
        return entregaRepository.findById(id).orElse(null); // retorna entrega ou null
    }

    // método para buscar entregas pelo CPF do cliente
    public List<Entrega> buscarPorCpf(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf).orElse(null); // busca cliente pelo CPF
        return entregaRepository.findByDestinatario(cliente); // retorna entregas desse cliente
    }
}