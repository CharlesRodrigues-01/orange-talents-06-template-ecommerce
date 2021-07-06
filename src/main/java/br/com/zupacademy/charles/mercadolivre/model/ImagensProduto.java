package br.com.zupacademy.charles.mercadolivre.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class ImagensProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotNull
    @Valid
    @JsonIgnore
    Produto produto;
    @URL
    @NotBlank
    String link;

    @Deprecated
    public ImagensProduto() {}

    public ImagensProduto(@NotNull @Valid Produto produto, @NotBlank String link) {
        this.produto = produto;
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public String getLink() {
        return link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImagensProduto that = (ImagensProduto) o;
        return Objects.equals(id, that.id) && Objects.equals(produto, that.produto) && Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, produto, link);
    }
}
