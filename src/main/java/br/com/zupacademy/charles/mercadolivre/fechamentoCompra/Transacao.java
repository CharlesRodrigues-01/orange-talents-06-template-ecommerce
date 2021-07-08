package br.com.zupacademy.charles.mercadolivre.fechamentoCompra;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private StatusTransacao status;
    private String idTransacaoGateway;
    private LocalDateTime instanteTransacao;
    @ManyToOne
    @JsonIgnore
    private Compra compra;

    @Deprecated
    public Transacao() {
    }

    public Transacao(StatusTransacao normaliza, String idTransacao, Compra compra) {

        this.status = normaliza;
        this.idTransacaoGateway = idTransacao;
        this.instanteTransacao = LocalDateTime.now();
        this.compra = compra;
    }

    public Long getId() {
        return id;
    }

    public StatusTransacao getStatus() {
        return status;
    }

    public String getIdTransacaoGateway() {
        return idTransacaoGateway;
    }

    public LocalDateTime getInstanteTransacao() {
        return instanteTransacao;
    }

    public Compra getCompra() {
        return compra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(idTransacaoGateway, transacao.idTransacaoGateway);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransacaoGateway);
    }

    public boolean concluidaComSucesso() {
        return this.status.equals(StatusTransacao.SUCESSO);
    }
}
