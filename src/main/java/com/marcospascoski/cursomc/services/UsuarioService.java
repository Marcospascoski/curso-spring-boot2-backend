package com.marcospascoski.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.marcospascoski.cursomc.security.UserSS;

public class UsuarioService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
