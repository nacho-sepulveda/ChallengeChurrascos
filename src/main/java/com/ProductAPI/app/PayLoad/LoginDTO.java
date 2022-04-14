package com.ProductAPI.app.PayLoad;

import java.io.Serializable;

public class LoginDTO implements Serializable{
   
	private static final long serialVersionUID = 1L;
	private String usernameOrEmail;
    private String password;
	public String getUsernameOrEmail() {
		return usernameOrEmail;
	}
	public void setUsernameOrEmail(String usernameOrEmail) {
		this.usernameOrEmail = usernameOrEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginDTO [usernameOrEmail=" + usernameOrEmail + ", password=" + password + "]";
	}
	
    
}
