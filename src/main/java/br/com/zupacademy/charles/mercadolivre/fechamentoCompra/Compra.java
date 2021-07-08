package br.com.zupacademy.charles.mercadolivre.fechamentoCompra;

import br.com.zupacademy.charles.mercadolivre.model.Produto;
import br.com.zupacademy.charles.mercadolivre.model.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Valid
    @ManyToOne
    private Produto produto;
    @Positive
    private int quantidade;
    @NotNull
    @Valid
    @ManyToOne
    private Usuario usuario;
    @Enumerated
    @NotNull
    private GatewayPagamento gateway;

    @Deprecated
    public Compra(){}

    public Compra(@NotNull @Valid Produto produto, @Positive int quantidade, @NotNull @Valid Usuario usuario,
                  @NotNull GatewayPagamento gateway) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.usuario = usuario;
        this.gateway = gateway;
    }

    public Long getId() { return id; }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public GatewayPagamento getGateway() { return gateway; }
}
