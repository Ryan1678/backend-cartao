package br.com.itb.miniprojetospring.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "OperacaoCartao")
public class OperacaoCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime dataCadastro;
    private String tipoOperacao; // ENTRADA ou SAÍDA
    private String codigoOperacao; // nullable
    @Column(precision = 8, scale = 2)
    private BigDecimal valor;
    private String statusCartao; // ATIVO, CONFIRMADO ou INATIVO

    @ManyToOne
    @JoinColumn(name = "cartao_id", nullable = false)
    private Cartao cartao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Construtor padrão
    public OperacaoCartao() {
    }

    // Construtor com parâmetros
    public OperacaoCartao(int id, LocalDateTime dataCadastro, String tipoOperacao,
                          String codigoOperacao, BigDecimal valor, String statusCartao,
                          Cartao cartao, Usuario usuario) {
        this.id = id;
        this.dataCadastro = dataCadastro;
        this.tipoOperacao = tipoOperacao;
        this.codigoOperacao = codigoOperacao;
        this.valor = valor;
        this.statusCartao = statusCartao;
        this.cartao = cartao;
        this.usuario = usuario;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getTipoOperacao() {
        return tipoOperacao;
    }
    public void setTipoOperacao(String tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public String getCodigoOperacao() {
        return codigoOperacao;
    }
    public void setCodigoOperacao(String codigoOperacao) {
        this.codigoOperacao = codigoOperacao;
    }

    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getStatusCartao() {
        return statusCartao;
    }
    public void setStatusCartao(String statusCartao) {
        this.statusCartao = statusCartao;
    }

    public Cartao getCartao() {
        return cartao;
    }
    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
