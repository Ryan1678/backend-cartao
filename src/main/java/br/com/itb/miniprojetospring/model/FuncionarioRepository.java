package br.com.itb.miniprojetospring.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Funcionario findAllById(long id);
    Optional<Funcionario> findByEmail(String email);
}