package br.com.itb.miniprojetospring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import br.com.itb.miniprojetospring.model.Produto;
import br.com.itb.miniprojetospring.model.ProdutoRepository;

@Service
public class ProdutoService {
    // Criar objeto repository
    final ProdutoRepository produtoRepository;

    // Injeção de Dependência
    public ProdutoService(ProdutoRepository _produtoRepository) {
        this.produtoRepository = _produtoRepository;
    }

    // Método INSERT INTO FUNCIONARIO
    @Transactional
    public Produto save(Produto _produto) {
        return produtoRepository.save(_produto);
    }

    // Método SELECT * FROM FUNCIONARIO
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findAllById(long id) {
        return produtoRepository.findById(id).orElse(null); // Use findById para retornar um Optional
    }

    // Método UPDATE FUNCIONARIO
    @Transactional
    public Produto update(Long id, Produto produto) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);

        if (optionalProduto.isPresent()) {
            Produto existingProduto = optionalProduto.get();
            // Atualiza os campos necessários
            existingProduto.setNome(produto.getNome());
            existingProduto.setPreco(produto.getPreco());
            existingProduto.setTipo(produto.getTipo());
            existingProduto.setDescricao(produto.getDescricao());

            // Salva as alterações no banco de dados
            return produtoRepository.save(existingProduto);
        } else {
            throw new RuntimeException("Produto não encontrado com o ID: " + id);
        }
    }

    // Método DELETE FUNCIONARIO
    @Transactional
    public void delete(long id) {
        produtoRepository.deleteById(id);
    }

}