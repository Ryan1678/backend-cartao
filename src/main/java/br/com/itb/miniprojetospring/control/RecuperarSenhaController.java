package br.com.itb.miniprojetospring.control;

import br.com.itb.miniprojetospring.model.Usuario;
import br.com.itb.miniprojetospring.model.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*") // Permite requisições do Flutter Web
public class RecuperarSenhaController {

    private final UsuarioRepository usuarioRepository;
    private final JavaMailSender mailSender;

    public RecuperarSenhaController(UsuarioRepository usuarioRepository, JavaMailSender mailSender) {
        this.usuarioRepository = usuarioRepository;
        this.mailSender = mailSender;
    }

    // Armazena temporariamente os códigos
    private final ConcurrentHashMap<String, String> resetCodes = new ConcurrentHashMap<>();

    // 1️⃣ Enviar código de recuperação
    @PostMapping("/recuperar-senha")
    public ResponseEntity<String> recuperarSenha(@RequestBody EmailRequest request) {
        String email = request.getEmail();

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("E-mail não cadastrado");
        }

        String codigo = String.format("%06d", new SecureRandom().nextInt(1_000_000));
        resetCodes.put(email, codigo);

        // Log no console para testes
        System.out.println("=== CÓDIGO GERADO PARA TESTES ===");
        System.out.println("E-mail: " + email + " | Código: " + codigo);
        System.out.println("=================================");

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Recuperação de Senha");
            message.setText("Seu código de verificação é: " + codigo);
            mailSender.send(message);
            System.out.println("E-mail enviado com sucesso para: " + email);
        } catch (Exception e) {
            System.out.println("Falha ao enviar e-mail (OK para testes): " + e.getMessage());
        }

        return ResponseEntity.ok("Código enviado com sucesso");
    }

    // 2️⃣ Confirmar reset de senha
    @PostMapping("/confirmar-reset")
    public ResponseEntity<String> confirmarReset(@RequestBody ResetSenhaRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(request.getEmail());
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("E-mail não cadastrado");
        }

        String codigoSalvo = resetCodes.get(request.getEmail());
        if (codigoSalvo == null || !codigoSalvo.equals(request.getCodigo())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Código inválido ou expirado");
        }

        Usuario usuario = usuarioOpt.get();
        usuario.setSenha(request.getNovaSenha()); // senha sem criptografia por enquanto
        usuarioRepository.save(usuario);

        resetCodes.remove(request.getEmail());
        return ResponseEntity.ok("Senha atualizada com sucesso");
    }
}

// DTOs
class EmailRequest {
    private String email;
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}

class ResetSenhaRequest {
    private String email;
    private String codigo;
    private String novaSenha;
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNovaSenha() { return novaSenha; }
    public void setNovaSenha(String novaSenha) { this.novaSenha = novaSenha; }
}
