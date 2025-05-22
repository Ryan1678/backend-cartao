package br.com.itb.miniprojetospring.control;

import br.com.itb.miniprojetospring.model.OperacaoCartao;
import br.com.itb.miniprojetospring.service.OperacaoCartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/operacoes-cartao")
public class OperacaoCartaoController {

    @Autowired
    private OperacaoCartaoService operacaoCartaoService;

    // GET /operacoes-cartao
    @GetMapping
    public List<OperacaoCartao> listarTodos() {
        return operacaoCartaoService.listarTodos();
    }

    // GET /operacoes-cartao/{id}
    @GetMapping("/{id}")
    public ResponseEntity<OperacaoCartao> buscarPorId(@PathVariable int id) {
        Optional<OperacaoCartao> operacao = operacaoCartaoService.buscarPorId(id);
        return operacao.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /operacoes-cartao
    @PostMapping
    public OperacaoCartao criar(@RequestBody OperacaoCartao operacaoCartao) {
        return operacaoCartaoService.salvar(operacaoCartao);
    }

    // PUT /operacoes-cartao/{id}
    @PutMapping("/{id}")
    public ResponseEntity<OperacaoCartao> atualizar(@PathVariable int id, @RequestBody OperacaoCartao operacaoCartao) {
        Optional<OperacaoCartao> existente = operacaoCartaoService.buscarPorId(id);
        if (existente.isPresent()) {
            operacaoCartao.setId(id);
            return ResponseEntity.ok(operacaoCartaoService.salvar(operacaoCartao));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /operacoes-cartao/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        Optional<OperacaoCartao> existente = operacaoCartaoService.buscarPorId(id);
        if (existente.isPresent()) {
            operacaoCartaoService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
