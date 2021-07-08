package br.com.zupacademy.charles.mercadolivre.responseDto;

import br.com.zupacademy.charles.mercadolivre.model.Opinioes;
import br.com.zupacademy.charles.mercadolivre.model.Produto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class DetalheProdutoResponseDto {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Set<DetalheProdutoCaracteristica> caracteristicas;
    private Set<String> linksImagens;
    private Set<String> perguntas;
    private Set<Map<String, String>> opinioes;
    private double mediaNotas;
    private Integer totalOpinioes;

    public DetalheProdutoResponseDto(Produto produto) {
        this.descricao = produto.getDescricao();
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.caracteristicas = produto.mapeiaCaracteristicas(DetalheProdutoCaracteristica::new);
        this.linksImagens = produto.mapeiaImagens(imagem -> imagem.getLink());
        this.perguntas = produto.mapeiaPerguntas(pergunta -> pergunta.getTitulo());

        Opinioes opinioes = produto.getOpinioes();
        this.opinioes = opinioes.mapeiaOpinioes(opiniao -> {
            return Map.of("titulo", opiniao.getTitulo(),
                    "descricao", opiniao.getDescricao());
        });

        this.mediaNotas = opinioes.media();
        this.totalOpinioes = opinioes.totalOpinioes();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getLinksImagens() {
        return linksImagens;
    }

    public Set<String> getPerguntas() {
        return perguntas;
    }

    public Set<Map<String, String>> getOpinioes() {
        return opinioes;
    }

    public double getMediaNotas() {
        return mediaNotas;
    }

    public Integer getTotalOpinioes() {
        return totalOpinioes;
    }
}
