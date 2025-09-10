package br.com.itb.miniprojetospring.control;

import br.com.itb.miniprojetospring.model.Cliente;
import br.com.itb.miniprojetospring.model.Usuario;
import br.com.itb.miniprojetospring.model.ClienteRepository;
import br.com.itb.miniprojetospring.model.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // ------------------ POST (cadastrar) ------------------
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody CadastroDTO dto) {
        // Criar usuário
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha()); // ⚠️ criptografar depois
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

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Cliente cliente = clienteRepository.findByUsuario(usuario);

        Map<String, Object> response = new HashMap<>();
        response.put("usuario", usuario);
        response.put("cliente", cliente);

        return ResponseEntity.ok(response);
    }

    // ------------------ PUT (atualizar) ------------------
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Integer id,
            @RequestBody CadastroDTO dto) {

        // Buscar usuário
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Atualizar dados de Usuario
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha()); // ⚠️ criptografar depois
        usuarioRepository.save(usuario);

        // Buscar cliente vinculado
        Cliente cliente = clienteRepository.findByUsuario(usuario);
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado");
        }

        cliente.setTipoCliente(dto.getTipoCliente());
        cliente.setDocumento(dto.getDocumento());
        cliente.setTelefone(dto.getTelefone());
        cliente.setDataNascimento(dto.getDataNascimento());
        clienteRepository.save(cliente);

        return ResponseEntity.ok("Cadastro atualizado com sucesso!");
    }

    // ------------------ DELETE (excluir) ------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Cliente cliente = clienteRepository.findByUsuario(usuario);
        if (cliente != null) {
            clienteRepository.delete(cliente);
        }

        usuarioRepository.delete(usuario);

        return ResponseEntity.ok("Cadastro deletado com sucesso!");
    }
}
