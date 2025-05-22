package br.com.itb.miniprojetospring.service;

import br.com.itb.miniprojetospring.model.Mensagem;
import br.com.itb.miniprojetospring.model.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensagemService {

    @Autowired
    private MensagemRepository mensagemRepository;

    // Listar todas as mensagens
    public List<Mensagem> listarTodos() {
        return mensagemRepository.findAll();
    }

    // Buscar mensagem por ID
    public Optional<Mensagem> buscarPorId(int id) {
        return mensagemRepository.findById(id);
    }

    // Criar ou atualizar mensagem
    public Mensagem salvar(Mensagem mensagem) {
        return mensagemRepository.save(mensagem);
    }

    // Deletar mensagem por ID
    public void deletar(int id) {
        mensagemRepository.deleteById(id);
    }
}
