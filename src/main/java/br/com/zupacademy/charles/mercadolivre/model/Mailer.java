package br.com.zupacademy.charles.mercadolivre.model;

public interface Mailer {
    void send(String body, String subject, String nomeFrom, String from, String to);
}
