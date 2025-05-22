package br.com.itb.miniprojetospring.control;

import br.com.itb.miniprojetospring.model.Cartao;
import br.com.itb.miniprojetospring.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    // GET /cartoes
    @GetMapping
    public List<Cartao> listarTodos() {
        return cartaoService.listarTodos();
    }

    // GET /cartoes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Cartao> buscarPorId(@PathVariable int id) {
        Optional<Cartao> cartao = cartaoService.buscarPorId(id);
        return cartao.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /cartoes
    @PostMapping
    public Cartao criar(@RequestBody Cartao cartao) {
        return cartaoService.salvar(cartao);
    }

    // PUT /cartoes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Cartao> atualizar(@PathVariable int id, @RequestBody Cartao cartao) {
        Optional<Cartao> existente = cartaoService.buscarPorId(id);
        if (existente.isPresent()) {
            cartao.setId(id);
            return ResponseEntity.ok(cartaoService.salvar(cartao));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /cartoes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        Optional<Cartao> existente = cartaoService.buscarPorId(id);
        if (existente.isPresent()) {
            cartaoService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
