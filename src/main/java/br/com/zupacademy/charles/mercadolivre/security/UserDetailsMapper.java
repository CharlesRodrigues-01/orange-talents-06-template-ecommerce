package br.com.zupacademy.charles.mercadolivre.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsMapper {

   UserDetails map(Object shouldBeASystemUser);
}
