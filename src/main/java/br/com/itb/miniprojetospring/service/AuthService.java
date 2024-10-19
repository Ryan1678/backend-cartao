package br.com.itb.miniprojetospring.service;

import br.com.itb.miniprojetospring.model.Funcionario;
import br.com.itb.miniprojetospring.model.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public boolean authenticate(String email, String senha) {
        return funcionarioRepository.findByEmail(email)
                .map(funcionario -> funcionario.getSenha().equals(senha)) // Verifica se a senha coincide
                .orElse(false); // Se o email não for encontrado, retorna false
    }
}
