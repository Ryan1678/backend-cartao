package br.com.itb.miniprojetospring.control;

import br.com.itb.miniprojetospring.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/mobile")
@CrossOrigin(origins = "*")
public class MobileLoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/login")
    public ResponseEntity<?> loginCliente(@RequestBody LoginRequest loginRequest) {

        // Busca usuário pelo email e senha
        Optional<Usuario> usuarioOpt = Optional.ofNullable(
                usuarioRepository.findByEmailAndSenha(loginRequest.getEmail(), loginRequest.getSenha())
        );

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(401)
                    .body(new ErrorResponse("Credenciais inválidas."));
        }

        Usuario usuario = usuarioOpt.get();

        // Verifica se é CLIENTE
        if (!usuario.getNivelAcesso().equalsIgnoreCase("CLIENTE")) {
            return ResponseEntity.status(403)
                    .body(new ErrorResponse("Acesso negado. Apenas clientes podem logar aqui."));
        }

        // Busca o cliente associado (pode ser null)
        Cliente cliente = clienteRepository.findByUsuario(usuario);

        // Retorna resposta completa
        return ResponseEntity.ok(new MobileLoginResponse(usuario, cliente));
    }

    // Classe interna para retorno de erros
    class ErrorResponse {
        public String error;

        public ErrorResponse(String message) {
            this.error = message;
        }
    }
}
