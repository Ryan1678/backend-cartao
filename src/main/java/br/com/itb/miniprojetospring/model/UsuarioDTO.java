package br.com.itb.miniprojetospring.model;

import java.time.LocalDateTime;

public class UsuarioDTO {
    private Integer id;
    private String nome;
    private String email;
    private String senha; // pode ser nulo no update
    private String nivelAcesso;
    private String foto; // base64 string
    private LocalDateTime dataCadastro;
    private String statusUsuario;

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getNivelAcesso() { return nivelAcesso; }
    public void setNivelAcesso(String nivelAcesso) { this.nivelAcesso = nivelAcesso; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }

    public String getStatusUsuario() { return statusUsuario; }
    public void setStatusUsuario(String statusUsuario) { this.statusUsuario = statusUsuario; }
}
