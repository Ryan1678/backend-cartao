package br.com.itb.miniprojetospring.control;

import br.com.itb.miniprojetospring.model.Cartao;
import br.com.itb.miniprojetospring.model.CartaoRepository;
import br.com.itb.miniprojetospring.model.Usuario;
import br.com.itb.miniprojetospring.model.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/cartao")
public class CartaoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Criar cartão
    @PostMapping
    public ResponseEntity<Cartao> criarCartao(@RequestBody Map<String, String> payload) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(Integer.parseInt(payload.get("usuarioId")));
        if (usuarioOpt.isEmpty()) return ResponseEntity.badRequest().build();

        Usuario usuario = usuarioOpt.get();
        Cartao cartao = new Cartao();
        cartao.setNome(payload.get("nome"));

        // Gera exatamente 16 dígitos
        Random random = new Random();
        StringBuilder numero = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            numero.append(random.nextInt(10));
        }
        cartao.setNumero(numero.toString());

        cartao.setDataCadastro(LocalDateTime.now());
        cartao.setSaldo(BigDecimal.ZERO);
        cartao.setCodigoResgate(""); // inicial vazio
        cartao.setStatusCartao("ATIVO");
        cartao.setUsuario(usuario);

        Cartao salvo = cartaoRepository.save(cartao);
        return ResponseEntity.ok(salvo);
    }

    // Listar todos os cartões
    @GetMapping
    public ResponseEntity<List<Cartao>> listarCartoes() {
        List<Cartao> cartoes = cartaoRepository.findAll();
        return ResponseEntity.ok(cartoes);
    }

    // Listar cartões por usuário
    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<Cartao>> listarCartoesPorUsuario(@PathVariable int usuarioId) {
        List<Cartao> cartoes = cartaoRepository.findByUsuarioId(usuarioId);
        return ResponseEntity.ok(cartoes);
    }

    // Operação de entrada (adicionar saldo)
    @PutMapping("/{id}/entrada")
    public ResponseEntity<?> entradaSaldo(@PathVariable int id, @RequestParam BigDecimal valor) {
        Optional<Cartao> cartaoOpt = cartaoRepository.findById(id);
        if (cartaoOpt.isEmpty()) return ResponseEntity.notFound().build();

        Cartao cartao = cartaoOpt.get();
        cartao.setSaldo(cartao.getSaldo().add(valor));
        cartaoRepository.save(cartao);

        return ResponseEntity.ok(cartao);
    }

    // Operação de saída (remover saldo)
    @PutMapping("/{id}/saida")
    public ResponseEntity<?> saidaSaldo(
            @PathVariable int id,
            @RequestParam BigDecimal valor,
            @RequestParam String codigoResgate) {

        Optional<Cartao> cartaoOpt = cartaoRepository.findById(id);
        if (cartaoOpt.isEmpty()) return ResponseEntity.notFound().build();

        Cartao cartao = cartaoOpt.get();

        // Verifica se o código informado é igual ao código do cartão
        if (!cartao.getCodigoResgate().equals(codigoResgate)) {
            return ResponseEntity.badRequest().body("Código de resgate inválido!");
        }

        if (cartao.getSaldo().compareTo(valor) < 0) {
            return ResponseEntity.badRequest().body("Saldo insuficiente");
        }

        cartao.setSaldo(cartao.getSaldo().subtract(valor));
        cartaoRepository.save(cartao);

        return ResponseEntity.ok(cartao);
    }

    // Endpoint para gerar código de retirada
    @PostMapping("/retirada/{cartaoId}")
    public ResponseEntity<Map<String, String>> gerarCodigoRetirada(@PathVariable int cartaoId) {
        Optional<Cartao> cartaoOpt = cartaoRepository.findById(cartaoId);
        if (cartaoOpt.isEmpty()) return ResponseEntity.notFound().build();

        Cartao cartao = cartaoOpt.get();
        String novoCodigo = String.format("%04d", new Random().nextInt(10000));
        cartao.setCodigoResgate(novoCodigo);
        cartaoRepository.save(cartao);

        Map<String, String> response = Map.of("codigoResgate", novoCodigo);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cartao> atualizarNomeCartao(@PathVariable int id, @RequestBody Map<String, String> payload) {
        Optional<Cartao> cartaoOpt = cartaoRepository.findById(id);
        if (cartaoOpt.isEmpty()) return ResponseEntity.notFound().build();

        Cartao cartao = cartaoOpt.get();
        String novoNome = payload.get("nome");
        if (novoNome != null && !novoNome.isBlank()) {
            cartao.setNome(novoNome);
            cartaoRepository.save(cartao);
            return ResponseEntity.ok(cartao);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // Deletar cartão
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCartao(@PathVariable int id) {
        Optional<Cartao> cartaoOpt = cartaoRepository.findById(id);
        if (cartaoOpt.isEmpty()) return ResponseEntity.notFound().build();

        cartaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
