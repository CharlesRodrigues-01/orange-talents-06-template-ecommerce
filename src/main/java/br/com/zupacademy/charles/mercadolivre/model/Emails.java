package br.com.zupacademy.charles.mercadolivre.model;

import br.com.zupacademy.charles.mercadolivre.fechamentoCompra.Compra;
import org.springframework.stereotype.Service;

@Service
public class Emails {

    Mailer mailer;

    public Emails(Mailer mailer) {
        this.mailer = mailer;
    }

    public void novaPergunta(Pergunta pergunta) {

        mailer.send("<html>...</html>", "nova pergunta...", "nome", pergunta.getUsuario().getEmail(),
                pergunta.getProduto().getUsuario().getEmail());
    }

    public void novaCompra(Compra novaCompra) {
        mailer.send("nova compra..." + novaCompra, "Você tem uma nova compra",
                novaCompra.getUsuario().getEmail(),
                "compras@nossomercadolivre.com",
                novaCompra.getProduto().getUsuario().getEmail());
    }
}
