package br.com.zupacademy.charles.mercadolivre.fechamentoCompra;

import br.com.zupacademy.charles.mercadolivre.model.Produto;
import br.com.zupacademy.charles.mercadolivre.model.Usuario;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

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

    public String urlRedirecionamento(
            UriComponentsBuilder uriComponentsBuilder) {
        return this.gateway.criaUrlRetorno(this, uriComponentsBuilder);
    }

    public void adicionaTransacao(RetornoGatewayPagamento form) {
        Transacao novaTransacao = form.toTransacao(this);
        Assert.isTrue(!this.transacoes.contains(novaTransacao), "Já existe uma transação igual a essa processada");

        Set<Transacao> transacoesConcluidasSucesso = getTransacoesConcluidasComSucesso();
        Assert.isTrue(transacoesConcluidasSucesso.isEmpty(), "Essa compra já foi concluída");

        this.transacoes.add(form.toTransacao(this));
    }

    private Set<Transacao> getTransacoesConcluidasComSucesso() {
        return this.transacoes.stream().filter(Transacao::concluidaComSucesso).collect(Collectors.toSet());
    }

    public boolean processadaComSucesso() {
        return !getTransacoesConcluidasComSucesso().isEmpty();
    }
}
