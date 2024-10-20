package br.com.itb.miniprojetospring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import br.com.itb.miniprojetospring.model.Pedido;
import br.com.itb.miniprojetospring.model.PedidoRepository;

@Service
public class PedidoService {
    // Criar objeto repository
    final PedidoRepository pedidoRepository;

    // Injeção de Dependência
    public PedidoService(PedidoRepository _pedidoRepository) {
        this.pedidoRepository = _pedidoRepository;
    }

    // Método INSERT INTO FUNCIONARIO
    @Transactional
    public Pedido save(Pedido _pedido) {
        return pedidoRepository.save(_pedido);
    }

    // Método SELECT * FROM FUNCIONARIO
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido findAllById(long id) {
        return pedidoRepository.findById(id).orElse(null); // Use findById para retornar um Optional
    }

    // Método UPDATE FUNCIONARIO
    @Transactional
    public Pedido update(Long id, Pedido pedido) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);

        if (optionalPedido.isPresent()) {
            Pedido existingPedido = optionalPedido.get();
            // Atualiza os campos necessários
            existingPedido.setEmail(pedido.getEmail());
            existingPedido.setEmissor(pedido.getEmissor());
            existingPedido.setMetodo(pedido.getMetodo());
            existingPedido.setStatus(pedido.getStatus());

            // Salva as alterações no banco de dados
            return pedidoRepository.save(existingPedido);
        } else {
            throw new RuntimeException("Pedido não encontrado com o ID: " + id);
        }
    }

    // Método DELETE FUNCIONARIO
    @Transactional
    public void delete(long id) {
        pedidoRepository.deleteById(id);
    }

}