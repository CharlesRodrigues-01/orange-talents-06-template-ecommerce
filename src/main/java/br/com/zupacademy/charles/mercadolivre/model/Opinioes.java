package br.com.zupacademy.charles.mercadolivre.model;

import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Opinioes {
    private final Set<Opiniao> opinioes;

    public Opinioes(Set<Opiniao> opinioes) {
    this.opinioes = opinioes;
    }

    public <T> Set<T> mapeiaOpinioes(Function<Opiniao, T> funcaoMapeadora) {
        return this.opinioes.stream().map(funcaoMapeadora).collect(Collectors.toSet());
    }

    public Double media() {
        Set<Integer> notas = mapeiaOpinioes(opiniao -> opiniao.getNota());

        OptionalDouble media = notas.stream().mapToInt(nota -> nota).average();
        return media.orElse(0.0);
    }

    public Integer totalOpinioes() {
        return this.opinioes.size();
    }
}
