package br.com.zupacademy.charles.mercadolivre.fechamentoCompra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagseguroRequestDto implements RetornoGatewayPagamento{

    @NotBlank
    private String idTransacao;
    @NotNull
    private StatusRetornoPagseguro status;


    public RetornoPagseguroRequestDto(@NotBlank String idTransacao, @NotNull StatusRetornoPagseguro status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    public Transacao toTransacao(Compra compra){
        return new Transacao(status.normaliza(), idTransacao, compra);
    }
}
