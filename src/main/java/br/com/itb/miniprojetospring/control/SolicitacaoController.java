package br.com.itb.miniprojetospring.control;

import br.com.itb.miniprojetospring.model.Cartao;
import br.com.itb.miniprojetospring.model.Solicitacao;
import br.com.itb.miniprojetospring.model.CartaoRepository;
import br.com.itb.miniprojetospring.service.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solicitacoes")
@CrossOrigin(origins = "*") // permite Flutter Web/Mobile
public class SolicitacaoController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private JavaMailSender mailSender;

    // GET /solicitacoes
    @GetMapping
    public List<Solicitacao> listarTodos() {
        return solicitacaoService.listarTodos();
    }

    // GET /solicitacoes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Solicitacao> buscarPorId(@PathVariable int id) {
        Optional<Solicitacao> solicitacao = solicitacaoService.buscarPorId(id);
        return solicitacao.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /solicitacoes
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Solicitacao solicitacao) {
        try {
            // ✅ 1. Buscar Cartão do banco
            Optional<Cartao> cartaoOpt = cartaoRepository.findById(solicitacao.getCartao().getId());
            if (!cartaoOpt.isPresent()) {
                return ResponseEntity.badRequest().body("Cartão não encontrado");
            }
            Cartao cartao = cartaoOpt.get();

            // ✅ 2. Criar a solicitação
            Solicitacao nova = new Solicitacao();
            nova.setCartao(cartao);
            nova.setValor(solicitacao.getValor());
            nova.setDataSolicitacao(LocalDateTime.now());

            Solicitacao criada = solicitacaoService.salvar(nova);

            // ✅ 3. Enviar e-mail (não quebra se falhar)
            try {
                String emailUsuario = cartao.getUsuario().getEmail();
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(emailUsuario);
                message.setSubject("Solicitação de Recarga");
                message.setText("Olá, foi solicitada uma recarga no cartão "
                        + cartao.getNumero()
                        + " no valor de R$ " + criada.getValor());
                mailSender.send(message);
            } catch (Exception e) {
                System.out.println("Erro ao enviar e-mail: " + e.getMessage());
            }

            return ResponseEntity.ok(criada);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao criar solicitação: " + e.getMessage());
        }
    }

    // PUT /solicitacoes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable int id, @RequestBody Solicitacao solicitacao) {
        Optional<Solicitacao> existente = solicitacaoService.buscarPorId(id);
        if (!existente.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        try {
            Solicitacao atualizada = existente.get();
            atualizada.setValor(solicitacao.getValor());

            // Se quiser trocar o cartão:
            if (solicitacao.getCartao() != null) {
                Optional<Cartao> cartaoOpt = cartaoRepository.findById(solicitacao.getCartao().getId());
                cartaoOpt.ifPresent(atualizada::setCartao);
            }

            Solicitacao salva = solicitacaoService.salvar(atualizada);
            return ResponseEntity.ok(salva);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao atualizar: " + e.getMessage());
        }
    }

    // DELETE /solicitacoes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable int id) {
        Optional<Solicitacao> existente = solicitacaoService.buscarPorId(id);
        if (!existente.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        try {
            solicitacaoService.deletar(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao deletar: " + e.getMessage());
        }
    }
}
