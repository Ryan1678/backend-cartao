package br.com.itb.miniprojetospring.service;

import br.com.itb.miniprojetospring.model.Cartao;
import br.com.itb.miniprojetospring.model.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    // Listar todos os cartões
    public List<Cartao> listarTodos() {
        return cartaoRepository.findAll();
    }

    // Buscar cartão por ID
    public Optional<Cartao> buscarPorId(int id) {
        return cartaoRepository.findById(id);
    }

    // Criar ou atualizar cartão
    public Cartao salvar(Cartao cartao) {
        return cartaoRepository.save(cartao);
    }

    // Deletar cartão por ID
    public void deletar(int id) {
        cartaoRepository.deleteById(id);
    }
}
