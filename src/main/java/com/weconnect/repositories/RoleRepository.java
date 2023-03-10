package com.weconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weconnect.models.Role;


public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByName(String name);

}
