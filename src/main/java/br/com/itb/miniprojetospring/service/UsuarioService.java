package br.com.itb.miniprojetospring.service;

import br.com.itb.miniprojetospring.model.Usuario;
import br.com.itb.miniprojetospring.model.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Listar todos os usuários
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // Buscar um usuário por ID
    public Optional<Usuario> buscarPorId(int id) {
        return usuarioRepository.findById(id);
    }

    // Criar ou atualizar um usuário
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Deletar um usuário pelo ID
    public void deletar(int id) {
        usuarioRepository.deleteById(id);
    }

    // Buscar por e-mail (ex: autenticação)
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    public List<Usuario> listarAdministradoresEVendedores() {
        List<Usuario> todos = usuarioRepository.findAll();
        return todos.stream()
                .filter(u -> u.getNivelAcesso().equalsIgnoreCase("ADMIN") ||
                        u.getNivelAcesso().equalsIgnoreCase("USER"))
                .collect(Collectors.toList());
    }

}
