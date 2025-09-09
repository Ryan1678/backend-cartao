package br.com.itb.miniprojetospring.control;

import br.com.itb.miniprojetospring.model.Mensagem;
import br.com.itb.miniprojetospring.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000") // Libera o front-end React
@RestController
@RequestMapping("/mensagens") // Corrigido para combinar com a URL do React
public class MensagemController {

    @Autowired
    private MensagemService mensagemService;

    // GET /api/mensagens
    @GetMapping
    public List<Mensagem> listarTodos() {
        return mensagemService.listarTodos();
    }

    // GET /api/mensagens/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Mensagem> buscarPorId(@PathVariable int id) {
        Optional<Mensagem> mensagem = mensagemService.buscarPorId(id);
        return mensagem.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/mensagens
    @PostMapping
    public Mensagem criar(@RequestBody Mensagem mensagem) {
        return mensagemService.salvar(mensagem);
    }

    // PUT /api/mensagens/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Mensagem> atualizar(@PathVariable int id, @RequestBody Mensagem mensagem) {
        Optional<Mensagem> existente = mensagemService.buscarPorId(id);
        if (existente.isPresent()) {
            mensagem.setId(id);
            return ResponseEntity.ok(mensagemService.salvar(mensagem));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/mensagens/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        Optional<Mensagem> existente = mensagemService.buscarPorId(id);
        if (existente.isPresent()) {
            mensagemService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
