package br.com.itb.miniprojetospring.control;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.itb.miniprojetospring.model.Produto;
import br.com.itb.miniprojetospring.service.ProdutoService;

@RestController
@CrossOrigin(origins = "", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/produto")
public class ProdutoController {

    final ProdutoService produtoService;

    public ProdutoController(ProdutoService _produtoService) {
        this.produtoService = _produtoService;
    }

    // ROTA POST - Criar novo funcionário
    @PostMapping
    public ResponseEntity<Object> saveProduto(@RequestBody Produto produto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtoService.save(produto));
    }

    // ROTA GET - Listar todos os funcionários
    @GetMapping
    public ResponseEntity<List<Produto>> getAllProdutos() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(produtoService.findAll());
    }

    // ROTA PUT - Atualizar funcionário existente
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduto(@PathVariable Long id, @RequestBody Produto produto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(produtoService.update(id, produto));
    }

    // ROTA DELETE - Excluir funcionário por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduto(@PathVariable long id) {
        produtoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}


