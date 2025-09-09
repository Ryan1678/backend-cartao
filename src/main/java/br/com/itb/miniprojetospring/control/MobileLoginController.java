package br.com.itb.miniprojetospring.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.itb.miniprojetospring.model.LoginRequest;
import br.com.itb.miniprojetospring.model.LoginResponse;
import br.com.itb.miniprojetospring.model.Usuario;
import br.com.itb.miniprojetospring.model.UsuarioRepository;

@RestController
@RequestMapping("/api/mobile") // Endpoint separado do web
@CrossOrigin(origins = "*") // Permite mobile/web simultâneo
public class MobileLoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> loginCliente(@RequestBody LoginRequest loginRequest) {
        Usuario usuario = usuarioRepository.findByEmailAndSenha(
                loginRequest.getEmail(), loginRequest.getSenha()
        );

        if (usuario == null) {
            // Credenciais inválidas
            return ResponseEntity.status(401).body(new ErrorResponse("Credenciais inválidas."));
        }

        // Apenas CLIENTE pode logar aqui
        if (!usuario.getNivelAcesso().equalsIgnoreCase("CLIENTE")) {
            return ResponseEntity.status(403).body(new ErrorResponse("Acesso negado. Apenas clientes podem logar aqui."));
        }

        // Login válido
        return ResponseEntity.ok(new LoginResponse(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getNivelAcesso()
        ));
    }

    // Classe interna para retornar erros
    class ErrorResponse {
        public String error;

        public ErrorResponse(String message) {
            this.error = message;
        }
    }
}
