package br.com.itb.miniprojetospring.service;

import br.com.itb.miniprojetospring.model.Funcionario;
import br.com.itb.miniprojetospring.model.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario authenticate(String email, String senha) {
        return funcionarioRepository.findByEmail(email)
                .filter(funcionario -> funcionario.getSenha().equals(senha)) // mantém só se senha bate
                .orElse(null); // retorna null se não encontrado ou senha errada
    }

}

