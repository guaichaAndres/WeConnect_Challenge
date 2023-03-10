package com.weconnect.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weconnect.models.User;
import com.weconnect.repositories.RoleRepository;
import com.weconnect.repositories.UserRepository;
import com.weconnect.services.UserService;
import com.weconnect.utils.NoFoundException;


@Service
public class UserServiceImpl implements UserService {

	private static final String USER_NOT_FOUND = "USER NOT FOUND";
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<User> findAll() {
		return null;
	}

	@Override
	public User saveEntity(User entity) {
		entity.setRole(roleRepository.findByName(entity.getRole().getName()));
		return userRepository.save(entity);
	}

	@Override
	public User findById(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (!userOptional.isPresent()) {
			throw new NoFoundException(USER_NOT_FOUND);
		}
		return userOptional.get();
	}

	@Override
	public User updateEntity(Long id, User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEntity(Long id) {
		userRepository.deleteById(id);
	}
	
	public User existsByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
