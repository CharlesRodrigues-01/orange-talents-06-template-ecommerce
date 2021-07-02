package br.com.zupacademy.charles.mercadolivre;

import br.com.zupacademy.charles.mercadolivre.requestDto.NovoUsuarioRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.h2.engine.Session;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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

    @Test
    @DisplayName("Deve cadastrar um novo Usuário")
    public void deveCadastrarUmNovoUsuario() throws Exception {

        mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new NovoUsuarioRequestDto("Teste@hotmail.com", "uhahsuhjk"))))
        .andExpect(status().isOk());
    }

    private String toJson(NovoUsuarioRequestDto requestDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(requestDto);
    }
}
