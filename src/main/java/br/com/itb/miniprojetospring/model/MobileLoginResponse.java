package br.com.itb.miniprojetospring.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MobileLoginResponse {

    private int id; // ID do usuário
    private String nome;
    private String email;
    private String nivelAcesso;
    private String fotoBase64;

    // Dados do cliente (opcional)
    private LocalDate dataNascimento;
    private String tipoCliente;
    private String documento;
    private String telefone;
    private LocalDateTime dataCadastroCliente;
    private String statusCliente;

    public MobileLoginResponse(Usuario usuario, Cliente cliente) {
        this.id = usuario.getId(); // importante para Flutter
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.nivelAcesso = usuario.getNivelAcesso();
        this.fotoBase64 = usuario.getFotoBase64();

        if (cliente != null) {
            this.dataNascimento = cliente.getDataNascimento();
            this.tipoCliente = cliente.getTipoCliente();
            this.documento = cliente.getDocumento();
            this.telefone = cliente.getTelefone();
            this.dataCadastroCliente = cliente.getDataCadastro();
            this.statusCliente = cliente.getStatusCliente();
        }
    }

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getNivelAcesso() { return nivelAcesso; }
    public String getFotoBase64() { return fotoBase64; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public String getTipoCliente() { return tipoCliente; }
    public String getDocumento() { return documento; }
    public String getTelefone() { return telefone; }
    public LocalDateTime getDataCadastroCliente() { return dataCadastroCliente; }
    public String getStatusCliente() { return statusCliente; }
}
