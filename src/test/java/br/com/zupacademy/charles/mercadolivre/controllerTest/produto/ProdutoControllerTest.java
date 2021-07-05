package br.com.zupacademy.charles.mercadolivre.controllerTest.produto;

import br.com.zupacademy.charles.mercadolivre.model.Categoria;
import br.com.zupacademy.charles.mercadolivre.model.Produto;
import br.com.zupacademy.charles.mercadolivre.model.Usuario;
import br.com.zupacademy.charles.mercadolivre.repository.CategoriaRepository;
import br.com.zupacademy.charles.mercadolivre.requestDto.CaracteristicaRequestDto;
import br.com.zupacademy.charles.mercadolivre.requestDto.CategoriaRequestDto;
import br.com.zupacademy.charles.mercadolivre.requestDto.NovoUsuarioRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Transactional
public class ProdutoControllerTest {

    @Autowired
    CategoriaRepository repository;

    CategoriaRequestDto categoriaRequestDto = new CategoriaRequestDto("categoria");
    Categoria categoria = categoriaRequestDto.toModel(repository);
    NovoUsuarioRequestDto requestDto = new NovoUsuarioRequestDto("aluno@email.com", "123456");
    Usuario usuario = requestDto.toModel();

    @DisplayName("Deve cadastrar um produto com mais de três características")
    @ParameterizedTest
    @MethodSource("geradorTeste1")
    void deveCadastrarUmProdutoComMaisDeTresCaracteristicas(Collection<CaracteristicaRequestDto> caracteristicas) throws Exception {

        new Produto("celular", 10, "uma descricao qualquer", BigDecimal.TEN, categoria,
                usuario, (List<CaracteristicaRequestDto>) caracteristicas);
    }

    static Stream<Arguments> geradorTeste1() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new CaracteristicaRequestDto("nome", "descricao"),
                                new CaracteristicaRequestDto("nome2", "descricao2"),
                                new CaracteristicaRequestDto("nome3", "descricao3")
                        )
                ),
                Arguments.of(
                        List.of(new CaracteristicaRequestDto("nome", "descricao"),
                                new CaracteristicaRequestDto("nome2", "descricao2"),
                                new CaracteristicaRequestDto("nome3", "descricao3"),
                                new CaracteristicaRequestDto("nome4", "descricao4")

                        )
                )
        );
    }


    @DisplayName("Não deve cadastrar um produto com menos de 3 caracteristicas")
    @ParameterizedTest
    @MethodSource("geradorTeste2")
    void naoDeveCadastrarUmProdutoComMenosDeTresCaracteristicas(Collection<CaracteristicaRequestDto> caracteristicas) throws Exception {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Produto("celular", 10, "uma descricao qualquer", BigDecimal.TEN,
                    categoria, usuario, (List<CaracteristicaRequestDto>) caracteristicas);
        });
    }

    static Stream<Arguments> geradorTeste2() {
        return Stream.of(
                Arguments.of(
                        List.of(new CaracteristicaRequestDto("nome", "descricao"),
                                new CaracteristicaRequestDto("nome2", "descricao2")

                        )
                ), Arguments.of(
                        List.of((new CaracteristicaRequestDto("nome", "descricao"))
                        )
                )
        );
    }

    @DisplayName("Não deve cadastrar um produto com caracteristicas repetidas")
    @ParameterizedTest
    @MethodSource("geradorTeste3")
    void naoDeveCadastrarUmProdutoComCaracteristicasRepetidas(Collection<CaracteristicaRequestDto> caracteristicas) throws Exception {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Produto("celular", 10, "uma descricao qualquer", BigDecimal.TEN,
                    categoria, usuario, (List<CaracteristicaRequestDto>) caracteristicas);
        });
    }

    static Stream<Arguments> geradorTeste3() {
        return Stream.of(
                Arguments.of(
                        List.of(new CaracteristicaRequestDto("nome", "descricao"),
                                new CaracteristicaRequestDto("nome", "descricao2"),
                                new CaracteristicaRequestDto("nome2", "descricao3")

                        )
                )
        );
    }
}
