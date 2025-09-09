package br.com.itb.miniprojetospring.control;

import java.time.LocalDate;

public class CadastroDTO {
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataNascimento;
    private String tipoCliente;
    private String documento;
    private String telefone;

    // Construtor vazio
    public CadastroDTO() {}

    // Construtor completo
    public CadastroDTO(String nome, String email, String senha,
                       LocalDate dataNascimento, String tipoCliente,
                       String documento, String telefone) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.tipoCliente = tipoCliente;
        this.documento = documento;
        this.telefone = telefone;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }
    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
