package br.com.itb.miniprojetospring.control;

import br.com.itb.miniprojetospring.model.Solicitacao;
import br.com.itb.miniprojetospring.service.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoController {

    @Autowired
        private SolicitacaoService solicitacaoService;

    // GET /mensagens
    @GetMapping
    public List<Solicitacao> listarTodos() {
        return solicitacaoService.listarTodos();
    }

    // GET /mensagens/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Solicitacao> buscarPorId(@PathVariable int id) {
        Optional<Solicitacao> solicitacao = solicitacaoService.buscarPorId(id);
        return solicitacao.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /mensagens
    @PostMapping
    public Solicitacao criar(@RequestBody Solicitacao solicitacao) {
        return solicitacaoService.salvar(solicitacao);
    }

    // PUT /mensagens/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Solicitacao> atualizar(@PathVariable int id, @RequestBody Solicitacao solicitacao) {
        Optional<Solicitacao> existente = solicitacaoService.buscarPorId(id);
        if (existente.isPresent()) {
            solicitacao.setId(id);
            return ResponseEntity.ok(solicitacaoService.salvar(solicitacao));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /mensagens/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        Optional<Solicitacao> existente = solicitacaoService.buscarPorId(id);
        if (existente.isPresent()) {
            solicitacaoService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}