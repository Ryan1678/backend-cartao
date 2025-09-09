package br.com.itb.miniprojetospring.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Métodos existentes
    Usuario findByEmailAndSenha(String email, String senha);

    Optional<Usuario> findByEmail(String email);

}
