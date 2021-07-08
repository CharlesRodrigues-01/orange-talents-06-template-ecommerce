package br.com.zupacademy.charles.mercadolivre.fechamentoCompra;

import br.com.zupacademy.charles.mercadolivre.model.Produto;
import br.com.zupacademy.charles.mercadolivre.model.Usuario;
import br.com.zupacademy.charles.mercadolivre.model.UsuarioLogado;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
@RequestMapping("/compras")
public class FechaCompra1Controller {

    @PersistenceContext
    EntityManager manager;

    @PostMapping
    @Transactional
    public String cadastrarCompra(@RequestBody @Valid NovaCompraRequestDto requestDto,
                                                @AuthenticationPrincipal UsuarioLogado usuarioLogado,
                                                UriComponentsBuilder uri) throws BindException {

        Usuario usuario = usuarioLogado.get();
        int quantidade = requestDto.getQuantidade();
        Produto produto = manager.find(Produto.class, requestDto.getIdProduto());
        boolean abatido = produto.abateEstoque(quantidade);
        if(abatido){
            GatewayPagamento gateway = requestDto.getGateway();
            Compra compra = new Compra(produto, quantidade, usuario, gateway);
            manager.persist(compra);
            if(gateway.equals(GatewayPagamento.PAGSEGURO)) {
                String urlPagseguro = uri.path("/retorno-pagseguro/{id}").buildAndExpand(compra.getId()).toString();
                return "pagseguro.com?returnId=" + compra.getId() + "&redirectUrl=" + urlPagseguro;

            } else {

                String urlPaypal = uri.path("/retorno-paypal/{id}").buildAndExpand(compra.getId()).toString();
                return "paypal.com?buyerId=" + compra.getId() + "&redirectUrl=" + urlPaypal;
            }
        }
        BindException problemaComEstoque = new BindException(requestDto, "novaCompraRequestDto");
        problemaComEstoque.reject(null, "Não foi possível realizar a compra pois o estoque é menor que a compra");
        throw problemaComEstoque;
    }
}
