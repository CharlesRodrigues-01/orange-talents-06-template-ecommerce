package br.com.zupacademy.charles.mercadolivre.fechamentoCompra;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraRequestDto {

    @Positive
    private Integer quantidade;
    @NotNull
    private Long idProduto;
    @NotNull
    private GatewayPagamento gateway;

    public NovaCompraRequestDto(Integer quantidade, @NotNull Long idProduto,
                                @NotNull GatewayPagamento gateway) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.gateway = gateway;
    }

    public Integer getQuantidade() { return quantidade; }

    public Long getIdProduto() { return idProduto; }

    public GatewayPagamento getGateway() { return gateway; }
}
