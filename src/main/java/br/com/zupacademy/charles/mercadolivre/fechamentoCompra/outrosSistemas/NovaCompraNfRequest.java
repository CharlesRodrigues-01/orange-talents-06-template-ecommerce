package br.com.zupacademy.charles.mercadolivre.fechamentoCompra.outrosSistemas;

import javax.validation.constraints.NotNull;

public class NovaCompraNfRequest {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idComprador;

    public NovaCompraNfRequest(Long idCompra, Long idComprador) {
        super();
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    @Override
    public String toString() {
        return "NovaCompraNFRequest [idCompra=" + idCompra + ", idComprador="
                + idComprador + "]";
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdComprador() {
        return idComprador;
    }
}
