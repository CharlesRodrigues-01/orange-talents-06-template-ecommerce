package br.com.zupacademy.charles.mercadolivre.fechamentoCompra;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class RetornoPaypalRequestDto implements RetornoGatewayPagamento{
    @Range(min = 0, max = 1)
    private int status;
    @NotNull
    private String idTransacao;

    public RetornoPaypalRequestDto(@Range(min = 0, max = 1) int status, @NotNull String idTransacao) {
        this.status = status;
        this.idTransacao = idTransacao;
    }

    public Transacao toTransacao(Compra compra) {
        StatusTransacao statusTransacao = this.status == 0 ? StatusTransacao.ERRO : StatusTransacao.SUCESSO;
        return new Transacao(statusTransacao, idTransacao, compra);
    }
}
