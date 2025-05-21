package br.com.itb.miniprojetospring.control;

import br.com.itb.miniprojetospring.model.Funcionario;
import br.com.itb.miniprojetospring.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:5173") // Permitir requisições do frontend React
public class LoginController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public Map<String, Object> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String senha = loginData.get("senha"); // Corrigido aqui para "senha" (igual no frontend)

        Map<String, Object> response = new HashMap<>();

        Funcionario funcionario = authService.authenticate(email, senha);
        // supondo que agora seu AuthService retorna Funcionario se ok, ou null se não

        if (funcionario != null) {
            response.put("success", true);
            response.put("funcionario", funcionario); // adiciona dados do funcionário no response
        } else {
            response.put("success", false);
            response.put("message", "Email ou Senha inválidas");
        }

        return response;
    }
}
