package br.com.zupacademy.charles.mercadolivre.controller;

import br.com.zupacademy.charles.mercadolivre.model.Usuario;
import br.com.zupacademy.charles.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.charles.mercadolivre.requestDto.NovoUsuarioRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid NovoUsuarioRequestDto usuarioRequestDto) {

        Usuario usuario = usuarioRequestDto.toModel();
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }

}
