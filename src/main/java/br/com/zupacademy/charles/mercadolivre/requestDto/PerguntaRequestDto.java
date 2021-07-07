package br.com.zupacademy.charles.mercadolivre.requestDto;

import br.com.zupacademy.charles.mercadolivre.model.Pergunta;
import br.com.zupacademy.charles.mercadolivre.model.Produto;
import br.com.zupacademy.charles.mercadolivre.model.Usuario;

import javax.validation.constraints.NotBlank;

public class PerguntaRequestDto {

    @NotBlank
    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Pergunta toModel(Usuario usuario, Produto produto) {
        return new Pergunta(this.titulo, usuario, produto);
    }
}
