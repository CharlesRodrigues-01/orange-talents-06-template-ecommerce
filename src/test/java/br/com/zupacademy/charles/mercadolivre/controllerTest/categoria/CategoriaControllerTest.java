package br.com.zupacademy.charles.mercadolivre.controllerTest.categoria;

import br.com.zupacademy.charles.mercadolivre.model.Categoria;
import br.com.zupacademy.charles.mercadolivre.requestDto.CategoriaRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class CategoriaControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    EntityManager entityManager;


    public List<Categoria> buscaLista() {
        List<Categoria> categorias = entityManager.createQuery("from Categoria a", Categoria.class).getResultList();
        return categorias;
    }

    private String toJson(CategoriaRequestDto requestDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(requestDto);
    }

    @Test
    @DisplayName("Deve cadastrar uma nova Categoria")
    public void deveCadastrarUmaCategoria() throws Exception {

        mockMvc.perform(post("/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new CategoriaRequestDto("Tecnologia"))))
                .andExpect(status().isOk());

        buscaLista();
        assertEquals(1, buscaLista().size());
    }

    @Test
    @DisplayName("Não deve cadastrar uma nova Categoria sem nome")
    public void naoDeveCadastrarUmaCategoriaSemNome() throws Exception {

        mockMvc.perform(post("/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new CategoriaRequestDto(""))))
                .andExpect(status().isBadRequest());

        buscaLista();
        assertTrue(buscaLista().isEmpty());
    }

}