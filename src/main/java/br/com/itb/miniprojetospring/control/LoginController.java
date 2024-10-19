package br.com.itb.miniprojetospring.control;

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
        String senha = loginData.get("password");

        Map<String, Object> response = new HashMap<>();
        if (authService.authenticate(email, senha)) {
            response.put("success", true);
        } else {
            response.put("success", false);
            response.put("message", "Email ou Senha inválidas");
        }

        return response;
    }
}
