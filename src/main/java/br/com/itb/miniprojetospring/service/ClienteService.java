package br.com.itb.miniprojetospring.service;

import br.com.itb.miniprojetospring.model.Cliente;
import br.com.itb.miniprojetospring.model.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Listar todos os clientes
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    // Buscar cliente por ID
    public Optional<Cliente> buscarPorId(int id) {
        return clienteRepository.findById(id);
    }

    // Criar ou atualizar cliente
    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Deletar cliente por ID
    public void deletar(int id) {
        clienteRepository.deleteById(id);
    }
}
