package br.com.itb.miniprojetospring.control;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.itb.miniprojetospring.model.Pedido;
import br.com.itb.miniprojetospring.service.PedidoService;

@RestController
@CrossOrigin(origins = "", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/pedido")
public class PedidoController {

    final PedidoService pedidoService;

    public PedidoController(PedidoService _pedidoService) {
        this.pedidoService = _pedidoService;
    }

    // ROTA POST - Criar novo funcionário
    @PostMapping
    public ResponseEntity<Object> savePedido(@RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoService.save(pedido));
    }

    // ROTA GET - Listar todos os funcionários
    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pedidoService.findAll());
    }

    // ROTA PUT - Atualizar funcionário existente
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pedidoService.update(id, pedido));
    }

    // ROTA DELETE - Excluir funcionário por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePedido(@PathVariable long id) {
        pedidoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}


