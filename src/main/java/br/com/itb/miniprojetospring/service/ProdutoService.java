package br.com.itb.miniprojetospring.service;

import br.com.itb.miniprojetospring.model.Produto;
import br.com.itb.miniprojetospring.model.ProdutoDTO;
import br.com.itb.miniprojetospring.model.ProdutoRepository;

import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Converter Produto -> ProdutoDTO (com imagem em Base64)
    private ProdutoDTO toDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setTipo(produto.getTipo());
        dto.setPreco(produto.getPreco());
        dto.setDescricao(produto.getDescricao());

        if (produto.getImagem() != null) {
            String base64Img = Base64.getEncoder().encodeToString(produto.getImagem());
            dto.setImagemBase64("data:image/png;base64," + base64Img);
        } else {
            dto.setImagemBase64(null);
        }
        return dto;
    }

    // Converter ProdutoDTO -> Produto (converte Base64 para byte[])
    private Produto fromDTO(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setId(dto.getId() != null ? dto.getId() : 0);

        // Converter Base64 para byte[]
        if (dto.getImagemBase64() != null && !dto.getImagemBase64().isEmpty()) {
            // Remove o prefixo data:image/png;base64, se existir
            String base64 = dto.getImagemBase64();
            if (base64.contains(",")) {
                base64 = base64.split(",")[1];
            }
            byte[] imagemBytes = Base64.getDecoder().decode(base64);
            produto.setImagem(imagemBytes);
        } else {
            produto.setImagem(null);
        }

        produto.setNome(dto.getNome());
        produto.setTipo(dto.getTipo());
        produto.setPreco(dto.getPreco());
        produto.setDescricao(dto.getDescricao());

        return produto;
    }

    public ProdutoDTO save(ProdutoDTO dto) {
        Produto produto = fromDTO(dto);
        Produto salvo = produtoRepository.save(produto);
        return toDTO(salvo);
    }

    public List<ProdutoDTO> findAll() {
        List<Produto> lista = produtoRepository.findAll();
        return lista.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ProdutoDTO findById(Long id) {
        Optional<Produto> opt = produtoRepository.findById(id);
        return opt.map(this::toDTO).orElse(null);
    }

    public ProdutoDTO update(Long id, ProdutoDTO dto) {
        Optional<Produto> opt = produtoRepository.findById(id);
        if (opt.isEmpty()) {
            return null;
        }
        Produto produtoExistente = opt.get();

        // Atualiza os campos
        produtoExistente.setNome(dto.getNome());
        produtoExistente.setTipo(dto.getTipo());
        produtoExistente.setPreco(dto.getPreco());
        produtoExistente.setDescricao(dto.getDescricao());

        // Atualiza imagem se enviada
        if (dto.getImagemBase64() != null && !dto.getImagemBase64().isEmpty()) {
            String base64 = dto.getImagemBase64();
            if (base64.contains(",")) {
                base64 = base64.split(",")[1];
            }
            produtoExistente.setImagem(Base64.getDecoder().decode(base64));
        }

        Produto atualizado = produtoRepository.save(produtoExistente);
        return toDTO(atualizado);
    }

    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }
}
