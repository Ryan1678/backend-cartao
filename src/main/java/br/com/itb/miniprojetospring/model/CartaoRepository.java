package br.com.itb.miniprojetospring.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartaoRepository extends JpaRepository<Cartao, Integer> {
    List<Cartao> findByUsuarioId(int usuarioId);
}
