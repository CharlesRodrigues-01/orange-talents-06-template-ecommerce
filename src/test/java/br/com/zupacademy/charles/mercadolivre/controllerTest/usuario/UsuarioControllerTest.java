package br.com.zupacademy.charles.mercadolivre.controllerTest.usuario;

import br.com.zupacademy.charles.mercadolivre.model.Usuario;
import br.com.zupacademy.charles.mercadolivre.requestDto.NovoUsuarioRequestDto;
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
public class UsuarioControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    EntityManager entityManager;

    public List<Usuario> buscaLista() {
        List<Usuario> usuarios = entityManager.createQuery("from Usuario a", Usuario.class).getResultList();
        return usuarios;
    }

    private String toJson(NovoUsuarioRequestDto requestDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(requestDto);
    }

    @Test
    @DisplayName("Deve cadastrar um novo Usuário")
    public void deveCadastrarUmNovoUsuario() throws Exception {

        mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new NovoUsuarioRequestDto("Teste@hotmail.com", "uhahsuhjk"))))
                .andExpect(status().isOk());

        buscaLista();
        assertEquals(1, buscaLista().size());
    }

    @Test
    @DisplayName("Não deve cadastrar um novo Usuário sem login")
    public void naoDeveCadastrarUmNovoUsuarioSemLogin() throws Exception {

        mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new NovoUsuarioRequestDto("", "uhahsuhjk"))))
                .andExpect(status().isBadRequest());

        buscaLista();
        assertTrue(buscaLista().isEmpty());
    }

    @Test
    @DisplayName("Não deve cadastrar um novo Usuário com e-mail inválido")
    public void naoDeveCadastrarUmNovoUsuarioComEmailInvalido() throws Exception {

        mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new NovoUsuarioRequestDto("emailInvalidoHotmail.com", "uhahsuhjk"))))
                .andExpect(status().isBadRequest());

        buscaLista();
        assertTrue(buscaLista().isEmpty());
    }

    @Test
    @DisplayName("Não deve cadastrar um novo Usuário sem senha")
    public void naoDeveCadastrarUmNovoUsuarioSemSenha() throws Exception {

        mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new NovoUsuarioRequestDto("Teste@hotmail.com", ""))))
                .andExpect(status().isBadRequest());

        buscaLista();
        assertTrue(buscaLista().isEmpty());
    }

    @Test
    @DisplayName("Não deve cadastrar um novo Usuário com senha menor que seis caracteres")
    public void naoCadastrarComSenhaMenorQueSeis() throws Exception {

        mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new NovoUsuarioRequestDto("Teste@hotmail.com", "senha"))))
                .andExpect(status().isBadRequest());

        buscaLista();
        assertTrue(buscaLista().isEmpty());
    }

}
