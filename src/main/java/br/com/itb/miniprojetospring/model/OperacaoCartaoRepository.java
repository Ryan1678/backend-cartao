package br.com.itb.miniprojetospring.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperacaoCartaoRepository extends JpaRepository<OperacaoCartao, Integer> {
    // Métodos customizados podem ser adicionados aqui se precisar
}
