package com.weconnect.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.weconnect.models.User;
import com.weconnect.repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		
		System.out.println("USUARIO -> " + user);
		
		if (user == null) {
			logger.error("Error en el login: No existe el usuario" + username);
			throw new UsernameNotFoundException("Error en el login: No existe el usuario" + username);
		}
		
		return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
																 .password(user.getPassword())
																 .roles(user.getRole().getName().toUpperCase())
																 .build();
	}

}

