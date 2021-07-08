package br.com.zupacademy.charles.mercadolivre.fechamentoCompra;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventosNovaCompra {

    private Set<EventoCompraSucesso> eventoCompraSucessos;

    public EventosNovaCompra(Set<EventoCompraSucesso> eventoCompraSucessos) {
        this.eventoCompraSucessos = eventoCompraSucessos;
    }

    public void processa(Compra compra) {
        if (compra.processadaComSucesso()) {
            eventoCompraSucessos.forEach(evento -> evento.processa(compra));
        }
    }
}
