package br.com.itb.miniprojetospring.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Cartao")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String numero;

    @Column(nullable = false)
    private LocalDateTime dataCadastro;

    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal saldo;

    @Column(nullable = false, length = 100)
    private String codigoResgate;

    @Column(nullable = false, length = 10)
    private String statusCartao; // ATIVO ou INATIVO

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Construtor padrão
    public Cartao() {
    }

    // Construtor com parâmetros (incluindo codigoResgate)
    public Cartao(int id, String nome, String numero, LocalDateTime dataCadastro, BigDecimal saldo, String codigoResgate, String statusCartao, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
        this.dataCadastro = dataCadastro;
        this.saldo = saldo;
        this.codigoResgate = codigoResgate;
        this.statusCartao = statusCartao;
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

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getCodigoResgate() {
        return codigoResgate;
    }
    public void setCodigoResgate(String codigoResgate) {
        this.codigoResgate = codigoResgate;
    }

    public String getStatusCartao() {
        return statusCartao;
    }
    public void setStatusCartao(String statusCartao) {
        this.statusCartao = statusCartao;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
