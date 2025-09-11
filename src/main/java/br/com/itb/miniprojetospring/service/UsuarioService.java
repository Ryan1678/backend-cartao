package br.com.itb.miniprojetospring.service;

import br.com.itb.miniprojetospring.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario salvarUsuario(UsuarioDTO dto) throws Exception {
        Usuario usuario = new Usuario();

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());

        if (dto.getSenha() == null || dto.getSenha().trim().isEmpty()) {
            throw new Exception("Senha é obrigatória na criação");
        }
        usuario.setSenha(dto.getSenha());

        usuario.setNivelAcesso(dto.getNivelAcesso());

        if (dto.getFoto() != null && !dto.getFoto().isEmpty()) {
            usuario.setFoto(converterBase64ParaByte(dto.getFoto()));
        }

        usuario.setDataCadastro(LocalDateTime.now());

        usuario.setStatusUsuario(dto.getStatusUsuario());

        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(UsuarioDTO dto) throws Exception {
        Optional<Usuario> optUsuario = usuarioRepository.findById(dto.getId());
        if (!optUsuario.isPresent()) {
            throw new Exception("Usuário não encontrado com id: " + dto.getId());
        }
        Usuario usuario = optUsuario.get();

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());

        if (dto.getSenha() != null && !dto.getSenha().trim().isEmpty()) {
            usuario.setSenha(dto.getSenha());
        }

        usuario.setNivelAcesso(dto.getNivelAcesso());

        if (dto.getFoto() != null && !dto.getFoto().isEmpty()) {
            usuario.setFoto(converterBase64ParaByte(dto.getFoto()));
        }

        // mantém dataCadastro original

        usuario.setStatusUsuario(dto.getStatusUsuario());

        return usuarioRepository.save(usuario);
    }

    public void deletarUsuario(int id) throws Exception {
        if (!usuarioRepository.existsById(id)) {
            throw new Exception("Usuário não encontrado com id: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    private byte[] converterBase64ParaByte(String base64) {
        if (base64.contains(",")) {
            base64 = base64.split(",")[1]; // remove prefixo tipo data:image/png;base64,
        }
        return Base64.getDecoder().decode(base64);
    }

    // Converter entidade para DTO (para enviar resposta)
    public UsuarioDTO converterParaDto(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        // por segurança, não envia senha no DTO
        dto.setSenha(null);
        dto.setNivelAcesso(usuario.getNivelAcesso());

        // converte byte[] foto para base64 string para enviar (opcional)
        if (usuario.getFoto() != null) {
            dto.setFoto("data:image/png;base64," + Base64.getEncoder().encodeToString(usuario.getFoto()));
        } else {
            dto.setFoto(null);
        }
        
        dto.setDataCadastro(usuario.getDataCadastro());
        dto.setStatusUsuario(usuario.getStatusUsuario());

        return dto;
    }
    @Autowired
    private CartaoRepository cartaoRepository;

    public List<Cartao> buscarCartoesPorUsuario(int usuarioId) {
        return cartaoRepository.findByUsuarioId(usuarioId);
    }
}