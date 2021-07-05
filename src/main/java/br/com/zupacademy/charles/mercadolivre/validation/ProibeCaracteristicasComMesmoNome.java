package br.com.zupacademy.charles.mercadolivre.validation;

import br.com.zupacademy.charles.mercadolivre.requestDto.ProdutoRequestDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class ProibeCaracteristicasComMesmoNome implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ProdutoRequestDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        ProdutoRequestDto request = (ProdutoRequestDto) o;

        Set<String> nomesIguais = request.buscaCaracteristicasIguais();
        if (!nomesIguais.isEmpty()) {
            errors.rejectValue("caracteristicas", null, "Não pode cadastrar características repetidas " + nomesIguais);
        }

    }
}
