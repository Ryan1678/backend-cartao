package br.com.itb.miniprojetospring.control;

import br.com.itb.miniprojetospring.model.Cliente;
import br.com.itb.miniprojetospring.model.Usuario;
import br.com.itb.miniprojetospring.model.ClienteRepository;
import br.com.itb.miniprojetospring.model.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody CadastroDTO dto) {
        // Criar usuário
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha()); // ⚠️ depois a gente criptografa
        usuario.setNivelAcesso("CLIENTE");
        usuario.setStatusUsuario("ATIVO");
        usuario.setDataCadastro(LocalDateTime.now());
        usuario = usuarioRepository.save(usuario);

        // Criar cliente
        Cliente cliente = new Cliente();
        cliente.setDataNascimento(dto.getDataNascimento());
        cliente.setTipoCliente(dto.getTipoCliente());
        cliente.setDocumento(dto.getDocumento());
        cliente.setTelefone(dto.getTelefone());
        cliente.setStatusCliente("ATIVO");
        cliente.setDataCadastro(LocalDateTime.now());
        cliente.setUsuario(usuario);

        clienteRepository.save(cliente);

        return ResponseEntity.ok("Cadastro realizado com sucesso!");
    }
}
