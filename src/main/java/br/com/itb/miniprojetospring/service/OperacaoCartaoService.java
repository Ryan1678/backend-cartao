package br.com.itb.miniprojetospring.service;

import br.com.itb.miniprojetospring.model.OperacaoCartao;
import br.com.itb.miniprojetospring.model.OperacaoCartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperacaoCartaoService {

    @Autowired
    private OperacaoCartaoRepository operacaoCartaoRepository;

    // Listar todas as operações de cartão
    public List<OperacaoCartao> listarTodos() {
        return operacaoCartaoRepository.findAll();
    }

    // Buscar operação por ID
    public Optional<OperacaoCartao> buscarPorId(int id) {
        return operacaoCartaoRepository.findById(id);
    }

    // Criar ou atualizar operação
    public OperacaoCartao salvar(OperacaoCartao operacaoCartao) {
        return operacaoCartaoRepository.save(operacaoCartao);
    }

    // Deletar operação por ID
    public void deletar(int id) {
        operacaoCartaoRepository.deleteById(id);
    }
}
