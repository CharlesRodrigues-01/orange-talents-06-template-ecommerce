package br.com.zupacademy.charles.mercadolivre.fechamentoCompra.outrosSistemas;

import javax.validation.constraints.NotNull;

public class RankingNovaCompraRequest {
    @NotNull
    private Long idCompra;
    @NotNull
    private Long idVendedor;

    public RankingNovaCompraRequest(Long idCompra, Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    @Override
    public String toString() {
        return "NovaCompraNFRequest [idCompra=" + idCompra + ", idComprador="
                + idVendedor + "]";
    }
}
