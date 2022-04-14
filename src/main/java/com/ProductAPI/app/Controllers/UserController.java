package com.ProductAPI.app.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProductAPI.app.DTOs.UserDTO;
import com.ProductAPI.app.EntityObjects.User;
import com.ProductAPI.app.ServiceImpl.UserServiceImpl;
import com.fasterxml.jackson.annotation.JsonBackReference;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserServiceImpl userService;
	
	@JsonBackReference
	@GetMapping("")
	public List<UserDTO> getAllUsers() {
		return userService.getAllUsers().stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
	}
	@GetMapping("/user/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Integer id) {
		User user = userService.getUserForId(id);
		// convert Entity to DTO
		UserDTO userResponse = modelMapper.map(user, UserDTO.class);
		return ResponseEntity.ok().body(userResponse);
	}
	@PutMapping("/user/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Integer id,@Valid @RequestBody UserDTO userDto) {
		// convert DTO to Entity
		User userRequest = modelMapper.map(userDto, User.class);
		User user = userService.updateUser(id, userRequest);
		// Entity to DTO
		UserDTO userResponse = modelMapper.map(user, UserDTO.class);
		return ResponseEntity.ok(userResponse);
	}
	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Integer id) {
		userService.deleteUser(id);
		return new ResponseEntity<String>("User successfully deleted ", HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*@PostMapping
	public void create(@RequestBody User user) {
		userService.save(user);
	}*/
}
