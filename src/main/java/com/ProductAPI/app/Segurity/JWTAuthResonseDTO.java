package com.ProductAPI.app.Segurity;

public class JWTAuthResonseDTO {
	private String accessToken;
	private String tokenType = "Bearer";

	
	public JWTAuthResonseDTO(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

	public JWTAuthResonseDTO(String accessToken, String tokenType) {
		super();
		this.accessToken = accessToken;
		this.tokenType = tokenType;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

}
