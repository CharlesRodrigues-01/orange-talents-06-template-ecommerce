package br.com.zupacademy.charles.mercadolivre.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SenhaLimpa {

    private String senha;

    public SenhaLimpa(@NotBlank @Size(min = 6) String senha) {
        Assert.hasLength(senha, "senha nao pode ser em branco");
        Assert.isTrue(senha.length()>=6,"senha tem que ter no mínimo 6 caracteres");

        this.senha = senha;
    }

    public String hash() {
        return new BCryptPasswordEncoder().encode(senha);
    }

}