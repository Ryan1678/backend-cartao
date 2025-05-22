package br.com.itb.miniprojetospring.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private LocalDate dataNascimento;
    private String tipoCliente;     // pode ser "PADRAO", "ALUNO", etc.
    private String documento;
    private String telefone;
    private LocalDateTime dataCadastro;
    private String statusCliente;   // ATIVO ou INATIVO

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Construtor padrão
    public Cliente() {
    }

    // Construtor com parâmetros
    public Cliente(int id, String nome, LocalDate dataNascimento, String tipoCliente,
                   String documento, String telefone, LocalDateTime dataCadastro,
                   String statusCliente, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.tipoCliente = tipoCliente;
        this.documento = documento;
        this.telefone = telefone;
        this.dataCadastro = dataCadastro;
        this.statusCliente = statusCliente;
        this.usuario = usuario;
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

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getStatusCliente() {
        return statusCliente;
    }
    public void setStatusCliente(String statusCliente) {
        this.statusCliente = statusCliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
