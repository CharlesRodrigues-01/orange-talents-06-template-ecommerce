package br.com.zupacademy.charles.mercadolivre.responseDto;

import br.com.zupacademy.charles.mercadolivre.model.Caracteristica;

public class DetalheProdutoCaracteristica {

    private final String nome;
    private final String descricao;

    public DetalheProdutoCaracteristica(Caracteristica caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
