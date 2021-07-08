package br.com.zupacademy.charles.mercadolivre.fechamentoCompra;

public interface RetornoGatewayPagamento {

    Transacao toTransacao(Compra compra);

}
