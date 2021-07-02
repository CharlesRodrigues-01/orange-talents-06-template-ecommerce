package br.com.zupacademy.charles.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String email;
    @NotBlank
    private String senha;
    @NotNull
    private LocalDateTime dataCadastro = LocalDateTime.now();

    public Usuario(@NotBlank String email, @NotBlank String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
}
