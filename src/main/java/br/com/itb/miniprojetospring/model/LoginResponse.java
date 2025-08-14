package br.com.itb.miniprojetospring.model;

public class LoginResponse {
    private String nome;
    private String email;
    private String nivelAcesso;

    public LoginResponse(String nome, String email, String nivelAcesso) {
        this.nome = nome;
        this.email = email;
        this.nivelAcesso = nivelAcesso;
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getNivelAcesso() { return nivelAcesso; }
}