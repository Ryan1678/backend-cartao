package br.com.itb.miniprojetospring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "Pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate data_hora_compra;
    private String emissor;
    private String email;
    private String metodo;
    private String status;

    // Construtor padrão
    public Pedido() {
    }

    // Construtor com parâmetros
    public Pedido(long id, String emissor, String email, String metodo) {
        this.id = id;
        this.emissor = emissor;
        this.email = email;
        this.metodo = metodo;
    }

    // Getters e Setters
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getData_hora_compra() {
        return data_hora_compra;
    }

    public void setData_hora_compra(LocalDate data_hora_compra) {
        this.data_hora_compra = data_hora_compra;
    }

    public String getEmissor() {
        return emissor;
    }
    public void setEmissor(String emissor) {
        this.emissor = emissor;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMetodo() {
        return metodo;
    }
    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}