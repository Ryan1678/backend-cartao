package br.com.itb.miniprojetospring.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Cliente findByUsuario(Usuario usuario);
    // Aqui você pode criar métodos de consulta personalizados se precisar futuramente
}
