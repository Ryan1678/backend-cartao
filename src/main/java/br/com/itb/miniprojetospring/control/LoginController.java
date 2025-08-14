package br.com.itb.miniprojetospring.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.itb.miniprojetospring.model.LoginRequest;
import br.com.itb.miniprojetospring.model.LoginResponse;
import br.com.itb.miniprojetospring.model.Usuario;
import br.com.itb.miniprojetospring.model.UsuarioRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // Ajuste para seu frontend
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public Object login(@RequestBody LoginRequest loginRequest) {
        Usuario usuario = usuarioRepository.findByEmailAndSenha(loginRequest.getEmail(), loginRequest.getSenha());

        if (usuario == null) {
            return new ErrorResponse("Credenciais inválidas.");
        }

        if (!usuario.getNivelAcesso().equalsIgnoreCase("ADMIN") &&
                !usuario.getNivelAcesso().equalsIgnoreCase("VENDEDOR")) {
            return new ErrorResponse("Acesso negado. Apenas ADMIN ou VENDEDOR.");
        }

        return new LoginResponse(usuario.getNome(), usuario.getEmail(), usuario.getNivelAcesso());
    }

    // Classe interna para erros
    class ErrorResponse {
        public String error;
        public ErrorResponse(String message) {
            this.error = message;
        }
    }
}