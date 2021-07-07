package br.com.zupacademy.charles.mercadolivre.controller;

import br.com.zupacademy.charles.mercadolivre.model.*;
import br.com.zupacademy.charles.mercadolivre.repository.PerguntaRepository;
import br.com.zupacademy.charles.mercadolivre.requestDto.PerguntaRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class PerguntaController {

    @PersistenceContext
    EntityManager manager;

    PerguntaRepository repository;
    Emails emails;

    public PerguntaController(PerguntaRepository repository, Emails emails) {
        this.repository = repository;
        this.emails = emails;
    }

    @PostMapping("/{id}/perguntas")
    @Transactional
    public ResponseEntity<Void> adicionar(@PathVariable("id") Long idPergunta,
                                          @RequestBody @Valid PerguntaRequestDto requestDto,
                                          @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        Usuario usuario = usuarioLogado.get();
        Produto produto = manager.find(Produto.class, idPergunta);

        Pergunta pergunta = requestDto.toModel(usuario, produto);
        repository.save(pergunta);

        emails.novaPergunta(pergunta);

        return ResponseEntity.ok().build();
    }
}
