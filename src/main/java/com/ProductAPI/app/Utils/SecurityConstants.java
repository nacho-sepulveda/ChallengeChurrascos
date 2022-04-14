package com.ProductAPI.app.Utils;

public class SecurityConstants {
	  public static final String SECRET = "SECRET_KEY";
	  public static final long EXPIRATION_TIME = 864000_000; // 10 days
	  public static final String TOKEN_PREFIX = "Bearer ";
	  public static final String HEADER_STRING = "Authorization";
	  public static final String SIGN_UP_URL = "/api/auth/signup";
}
