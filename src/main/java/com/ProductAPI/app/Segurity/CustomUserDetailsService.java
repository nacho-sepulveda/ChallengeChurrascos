package com.ProductAPI.app.Segurity;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ProductAPI.app.EntityObjects.Role;
import com.ProductAPI.app.EntityObjects.User;
import com.ProductAPI.app.Repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		User user = userRepo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
			.orElseThrow(() -> new UsernameNotFoundException("User not found with that username or email" + usernameOrEmail));
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mappingRoles(user.getRoles()));
		
	}

	private Collection<? extends GrantedAuthority> mappingRoles(Set<Role> roles) {
		return roles.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
