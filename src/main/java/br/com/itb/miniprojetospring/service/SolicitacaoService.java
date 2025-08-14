package br.com.itb.miniprojetospring.service;

import br.com.itb.miniprojetospring.model.SolicitacaoRepository;
import br.com.itb.miniprojetospring.model.Solicitacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoService {

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    // Listar todas as mensagens
        public List<Solicitacao> listarTodos() {
        return solicitacaoRepository.findAll();
    }

    // Buscar mensagem por ID
    public Optional<Solicitacao> buscarPorId(int id) {
        return solicitacaoRepository.findById(id);
    }

    // Criar ou atualizar mensagem
    public Solicitacao salvar(Solicitacao solicitacao) {
        return solicitacaoRepository.save(solicitacao);
    }

    // Deletar mensagem por ID
    public void deletar(int id) {
        solicitacaoRepository.deleteById(id);
    }
}