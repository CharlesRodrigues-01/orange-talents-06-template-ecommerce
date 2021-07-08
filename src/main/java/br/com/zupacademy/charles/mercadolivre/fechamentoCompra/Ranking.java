package br.com.zupacademy.charles.mercadolivre.fechamentoCompra;

import io.jsonwebtoken.lang.Assert;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Ranking implements EventoCompraSucesso{
    @Override
    public void processa(Compra compra) {
        Assert.isTrue(compra.processadaComSucesso(), "Compra não processada com sucesso!");
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idVendedor", compra.getProduto().getUsuario().getId());
        restTemplate.postForEntity("http://localhost:8080/ranking", request, String.class);
    }
}