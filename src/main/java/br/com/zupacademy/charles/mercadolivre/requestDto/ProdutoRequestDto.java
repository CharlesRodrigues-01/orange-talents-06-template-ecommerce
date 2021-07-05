package br.com.zupacademy.charles.mercadolivre.requestDto;

import br.com.zupacademy.charles.mercadolivre.annotation.ExistId;
import br.com.zupacademy.charles.mercadolivre.annotation.UniqueValue;
import br.com.zupacademy.charles.mercadolivre.model.Categoria;
import br.com.zupacademy.charles.mercadolivre.model.Produto;
import br.com.zupacademy.charles.mercadolivre.model.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProdutoRequestDto {

    @NotBlank
    @UniqueValue(fieldName = "nome", domainClass = Produto.class)
    private String nome;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    @Positive
    private Integer quantidade;
    @NotBlank
    @Length(max = 1000)
    private String descricao;
    @NotNull
    @ExistId(fieldName = "id", domainClass = Categoria.class)
    private Long idCategoria;
    @Size(min = 3)
    @Valid
    private List<CaracteristicaRequestDto> caracteristicas = new ArrayList<>();

    public Set<String> buscaCaracteristicasIguais() {
        HashSet<String> nomesIguais = new HashSet<>();
        HashSet<String> resultados = new HashSet<>();

        for (CaracteristicaRequestDto caracteristica : caracteristicas) {
            String nome = caracteristica.getNome();

            if (!nomesIguais.add(nome)) {
                resultados.add(nome);
            }
        }
        return resultados;
    }

    public ProdutoRequestDto(@NotBlank String nome, @NotNull BigDecimal valor, @NotNull Integer quantidade,
                             @NotBlank String descricao, @NotNull Long idCategoria,
                             @Valid List<CaracteristicaRequestDto> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.caracteristicas.addAll(caracteristicas);
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public List<CaracteristicaRequestDto> getCaracteristicas() {
        return caracteristicas;
    }

    public Produto toModel(EntityManager manager, Usuario usuario) {
        @NotNull Categoria categoria = manager.find(Categoria.class, idCategoria);
        Assert.state(categoria != null, "Esta categoria não existe no banco de dados " + idCategoria);

        return new Produto(nome, quantidade, descricao, valor, categoria, usuario, caracteristicas);
    }

}
