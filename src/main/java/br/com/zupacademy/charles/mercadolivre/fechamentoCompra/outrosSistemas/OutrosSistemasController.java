package br.com.zupacademy.charles.mercadolivre.fechamentoCompra.outrosSistemas;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OutrosSistemasController {

    @PostMapping(value = "/notas-fiscais")
    public void criaNota(@Valid @RequestBody NovaCompraNfRequest form) throws InterruptedException {
        System.out.println("criando nota "+form);
        Thread.sleep(150);
    }

    @PostMapping(value = "/ranking")
    public void ranking(@Valid @RequestBody RankingNovaCompraRequest form) throws InterruptedException {
        System.out.println("criando ranking"+form);
        Thread.sleep(150);
    }
}
