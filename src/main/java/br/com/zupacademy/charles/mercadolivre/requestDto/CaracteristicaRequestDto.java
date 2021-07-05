package br.com.zupacademy.charles.mercadolivre.requestDto;

import br.com.zupacademy.charles.mercadolivre.model.Caracteristica;
import br.com.zupacademy.charles.mercadolivre.model.Produto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CaracteristicaRequestDto {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public CaracteristicaRequestDto(@NotBlank String nome, @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }

    public Caracteristica toModel(@NotNull @Valid Produto produto){
        return new Caracteristica(nome, descricao, produto);

    }
}
