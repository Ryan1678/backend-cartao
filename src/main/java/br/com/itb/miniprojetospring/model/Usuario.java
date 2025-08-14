package br.com.itb.miniprojetospring.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Base64;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String email;
    private String senha;
    private String nivelAcesso;

    @Lob
    @JsonIgnore // Não envia o byte[] direto no JSON
    private byte[] foto;

    private LocalDateTime dataCadastro;
    private String statusUsuario;

    // Construtor padrão
    public Usuario() {
    }

    // Construtor com parâmetros
    public Usuario(int id, String nome, String email, String senha, String nivelAcesso, byte[] foto, LocalDateTime dataCadastro, String statusUsuario) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
        this.foto = foto;
        this.dataCadastro = dataCadastro;
        this.statusUsuario = statusUsuario;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

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

    public String getNivelAcesso() {
        return nivelAcesso;
    }
    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public byte[] getFoto() {
        return foto;
    }
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getStatusUsuario() {
        return statusUsuario;
    }
    public void setStatusUsuario(String statusUsuario) {
        this.statusUsuario = statusUsuario;
    }

    // Envia a foto no JSON como base64
    @JsonProperty("foto")
    public String getFotoBase64() {
        if (this.foto != null && this.foto.length > 0) {
            return Base64.getEncoder().encodeToString(this.foto);
        }
        return null;
    }

    // Recebe foto base64 e converte para byte[]
    @JsonProperty("foto")
    public void setFotoBase64(String fotoBase64) {
        if (fotoBase64 != null && !fotoBase64.isEmpty()) {
            this.foto = Base64.getDecoder().decode(fotoBase64);
        } else {
            this.foto = null;
        }
    }
}
