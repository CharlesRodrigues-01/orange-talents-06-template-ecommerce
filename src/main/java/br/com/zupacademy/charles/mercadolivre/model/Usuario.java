package br.com.zupacademy.charles.mercadolivre.model;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

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

    public Usuario(@NotBlank String email, @NotBlank SenhaLimpa senhaLimpa) {

        Assert.isTrue(StringUtils.hasLength(email),"O email não pode estar em branco");
        Assert.notNull(senhaLimpa,"O objeto do tipo senhaLimpa nao pode ser nulo");

        this.email = email;
        this.senha = senhaLimpa.hash();;
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
