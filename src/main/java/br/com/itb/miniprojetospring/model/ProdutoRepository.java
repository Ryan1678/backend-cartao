package br.com.itb.miniprojetospring.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Consulta por ID, mais correta e segura
    Optional<Produto> findById(Long id);

    // (Opcional) Você pode adicionar métodos personalizados aqui depois
}
