package br.com.zupacademy.charles.mercadolivre.fechamentoCompra;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class FechamentoCompra2Controller {

    @PersistenceContext
    private EntityManager manager;

    private EventosNovaCompra eventosNovaCompra;

    public FechamentoCompra2Controller(EventosNovaCompra eventosNovaCompra) {
        this.eventosNovaCompra = eventosNovaCompra;
    }

    @PostMapping("/retorno-pagseguro/{id}")
    @Transactional
    public String processamentoPagseguro(@PathVariable("id") Long idCompra,
                                         @Valid RetornoPagseguroRequestDto requestDto) {

        return processa(idCompra, requestDto);
    }

    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public String processamentoPaypal(@PathVariable("id") Long idCompra,
                                      @Valid RetornoPaypalRequestDto requestDto) {
        return processa(idCompra, requestDto);
    }

    private String processa(Long idCompra, RetornoGatewayPagamento retornoGatewayPagamento) {
        Compra compra = manager.find(Compra.class, idCompra);
        compra.adicionaTransacao(retornoGatewayPagamento);
        manager.merge(compra);
        eventosNovaCompra.processa(compra);
        return compra.toString();
    }
}
