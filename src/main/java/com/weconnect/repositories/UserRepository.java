package com.weconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weconnect.models.User;



public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);

}
