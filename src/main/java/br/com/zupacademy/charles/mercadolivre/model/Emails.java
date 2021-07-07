package br.com.zupacademy.charles.mercadolivre.model;

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
}
