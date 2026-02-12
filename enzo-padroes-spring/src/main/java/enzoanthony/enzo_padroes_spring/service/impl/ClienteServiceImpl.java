package enzoanthony.enzo_padroes_spring.service.impl;

import enzoanthony.enzo_padroes_spring.exception.ResourceNotFoundException;
import enzoanthony.enzo_padroes_spring.model.Cliente;
import enzoanthony.enzo_padroes_spring.model.ClienteRepository;
import enzoanthony.enzo_padroes_spring.model.Endereco;
import enzoanthony.enzo_padroes_spring.model.EnderecoRepository;
import enzoanthony.enzo_padroes_spring.service.ClienteService;
import enzoanthony.enzo_padroes_spring.service.ViaCepService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ClienteServiceImpl.class);

    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final ViaCepService viaCepService;

    public ClienteServiceImpl(
            ClienteRepository clienteRepository,
            EnderecoRepository enderecoRepository,
            ViaCepService viaCepService
    ) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.viaCepService = viaCepService;
    }

    @Override
    public Iterable<Cliente> ListarTodos() {
        logger.info("Listando todos os clientes");
        return clienteRepository.findAll();
    }

    @Override
    public Cliente ListarPorId(Long id) {
        logger.info("Buscando cliente por id {}", id);
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }

    @Override
    public void insert(Cliente cliente) {
        logger.info("Inserindo cliente {}", cliente.getNome());
        salvarClienteComCep(cliente);
    }

    private void salvarClienteComCep(Cliente cliente) {

        if (cliente.getEndereco() == null || cliente.getEndereco().getCep() == null) {
            throw new IllegalArgumentException("CEP obrigatório");
        }

        String cep = cliente.getEndereco().getCep();

        Endereco endereco = enderecoRepository.findById(cep)
                .orElseGet(() -> {
                    logger.info("Buscando endereço no ViaCEP {}", cep);
                    Endereco novoEndereco = viaCepService.ConsultarCep(cep);
                    if (novoEndereco == null) {
                        throw new ResourceNotFoundException("CEP inválido");
                    }
                    enderecoRepository.save(novoEndereco);
                    return novoEndereco;
                });

        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);

        logger.info("Cliente salvo com sucesso");
    }

    @Override
    public void update(Long id, Cliente cliente) {
        logger.info("Atualizando cliente {}", id);

        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        cliente.setId(clienteExistente.getId());
        salvarClienteComCep(cliente);
    }

    @Override
    public void delete(Long id) {
        logger.info("Removendo cliente {}", id);

        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }

        clienteRepository.deleteById(id);
    }
}
