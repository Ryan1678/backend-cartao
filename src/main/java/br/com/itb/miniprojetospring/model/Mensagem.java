package br.com.itb.miniprojetospring.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Mensagem")
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime dataMensagem;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = true) // Agora o usuário é opcional
    private Usuario usuario;

    private String telefone;

    private String titulo;

    @Column(length = 400, nullable = false)
    private String texto;

    private String statusMensagem; // ATIVO ou INATIVO

    // Construtor padrão
    public Mensagem() {
    }

    // Construtor com parâmetros
    public Mensagem(int id, LocalDateTime dataMensagem, Usuario usuario, String telefone,
                    String titulo, String texto, String statusMensagem) {
        this.id = id;
        this.dataMensagem = dataMensagem;
        this.usuario = usuario;
        this.telefone = telefone;
        this.titulo = titulo;
        this.texto = texto;
        this.statusMensagem = statusMensagem;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataMensagem() {
        return dataMensagem;
    }
    public void setDataMensagem(LocalDateTime dataMensagem) {
        this.dataMensagem = dataMensagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getStatusMensagem() {
        return statusMensagem;
    }
    public void setStatusMensagem(String statusMensagem) {
        this.statusMensagem = statusMensagem;
    }
}
