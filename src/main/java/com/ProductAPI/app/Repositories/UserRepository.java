package com.ProductAPI.app.Repositories;


import com.ProductAPI.app.EntityObjects.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public Optional<User> findByEmail(String email);
	
	public Optional<User> findByUsername(String username);
	
	public Optional<User> findByUsernameOrEmail(String usernmae, String email);
	
	public Boolean existsByUsername(String username);
	
	public Boolean existsByEmail(String email);
	
	
}
