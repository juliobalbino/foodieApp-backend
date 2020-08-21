package com.foodie.foodieApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.foodie.foodieApp.entities.Usuario;
import com.foodie.foodieApp.repositories.UsuarioRepository;
import com.foodie.foodieApp.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario us = repo.findByEmail(email);
		if (us == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(us.getId(), us.getEmail(), us.getSenha(), us.getPerfis());
	}
}
