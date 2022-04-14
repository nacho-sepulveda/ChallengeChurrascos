package com.ProductAPI.app.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProductAPI.app.EntityObjects.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	public Optional<Role> findByName(String name);
	//public Optional<Role> findByName(String name);
	
}