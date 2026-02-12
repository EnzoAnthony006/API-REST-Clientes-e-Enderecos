package enzoanthony.enzo_padroes_spring.service;

import enzoanthony.enzo_padroes_spring.model.Cliente;

public interface ClienteService {
    Iterable<Cliente> ListarTodos();

    Cliente ListarPorId(Long id);

    void insert(Cliente cliente);

    void update(Long id, Cliente cliente);

    void delete(Long id);
}
