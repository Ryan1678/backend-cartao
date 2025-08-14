package br.com.itb.miniprojetospring.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Solicitacao")
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "dataSolicitação")
    private LocalDateTime dataSolicitacao;

    @ManyToOne
    @JoinColumn(name = "cartao_id", nullable = false)
    private Cartao cartao;

    private BigDecimal valor;

    // Construtor padrão
    public Solicitacao() {
    }

    // Construtor com parâmetros
    public Solicitacao(int id, LocalDateTime dataSolicitacao, Cartao cartao, BigDecimal valor) {
        this.id = id;
        this.dataSolicitacao = dataSolicitacao;
        this.cartao = cartao;
        this.valor = valor;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }
    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Cartao getCartao() {
        return cartao;
    }
    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}