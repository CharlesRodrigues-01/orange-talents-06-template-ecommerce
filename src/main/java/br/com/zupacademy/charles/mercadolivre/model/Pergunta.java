package br.com.zupacademy.charles.mercadolivre.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotNull
    private LocalDateTime instanteCriacao = LocalDateTime.now();
    @NotNull
    @ManyToOne
    private Usuario usuario;
    @NotNull
    @ManyToOne
    @JsonIgnore
    private Produto produto;

    @Deprecated
    public Pergunta(){}

    public Pergunta(@NotBlank String titulo, @NotNull Usuario usuario, @NotNull Produto produto) {
        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }
}
