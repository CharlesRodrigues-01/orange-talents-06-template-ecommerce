package br.com.zupacademy.charles.mercadolivre.requestDto;

import br.com.zupacademy.charles.mercadolivre.annotation.UniqueValue;
import br.com.zupacademy.charles.mercadolivre.model.SenhaLimpa;
import br.com.zupacademy.charles.mercadolivre.model.Usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NovoUsuarioRequestDto {

    @NotBlank
    @Email
    @UniqueValue(fieldName = "email", domainClass = Usuario.class)
    private String email;
    @NotBlank
    @Size(min = 6)
    private String senha;

    public NovoUsuarioRequestDto(@NotBlank String email, @NotBlank String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario toModel() {
        return new Usuario(email, new SenhaLimpa(senha));
    }

}
