package com.ProductAPI.app.Service;

import java.util.List;

import com.ProductAPI.app.EntityObjects.User;



public interface UserService {
	public List<User> getAllUsers();
	public User getUserForId(Integer id);
	public User updateUser(Integer id, User user);
	public void deleteUser(Integer id);
	
}
