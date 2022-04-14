package com.ProductAPI.app.ServiceImpl;



import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ProductAPI.app.EntityObjects.User;
import com.ProductAPI.app.Errors.ResourceNotFoundException;
import com.ProductAPI.app.Repositories.RoleRepository;
import com.ProductAPI.app.Repositories.UserRepository;
import com.ProductAPI.app.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	@Qualifier("userRepo")
	private UserRepository userRepo;
	@Autowired
	@Qualifier("roleRepo")
	private RoleRepository roleRepo;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	
	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	@Override
	public User getUserForId(Integer id) {
		Optional<User> result = userRepo.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new ResourceNotFoundException("User", "id", id);
		}
	}
	@Override
	public User updateUser(Integer id, User userRequest) {
		User user = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		user.setFirst_name(userRequest.getFirst_name());
		user.setLast_name(userRequest.getLast_name());
		user.setEmail(userRequest.getEmail());
		user.setUsername(userRequest.getUsername());
		user.setUpdated(new Date());
		user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRoles(userRequest.getRoles());
	    return userRepo.save(user);
	}
	@Override
	public void deleteUser(Integer id) {
		userRepo.deleteById(id);
	}

	/*public void validateComplete(String first_name, String last_name, String email, String password) throws Exception {
		validateBasic(first_name, last_name, email);
		if (!userRepo.findByEmail(email).isEmpty()) {
			throw new Exception("Email is already in use");
		}
		if (password == null) {
			throw new Exception("The password cannot be null.");
		}
	}*/

	/*public void validateBasic(String first_name, String last_name, String email) throws Exception {
		if (first_name == null || first_name.trim().isEmpty()) {
			throw new Exception("The first name cannot be null");
		}
		if (last_name == null || last_name.trim().isEmpty()) {
			throw new Exception("The last name cannot be null");
		}
		if (email == null || email.trim().isEmpty()) {
			throw new Exception("The email cannot be null");
		}
	}*/



	
	
	
	
	
	

}
