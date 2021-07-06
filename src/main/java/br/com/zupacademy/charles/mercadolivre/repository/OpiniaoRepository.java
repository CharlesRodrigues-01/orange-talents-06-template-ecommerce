package br.com.zupacademy.charles.mercadolivre.repository;

import br.com.zupacademy.charles.mercadolivre.model.Opiniao;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;

@ReadingConverter
public interface OpiniaoRepository extends JpaRepository<Opiniao, Long> {
}
