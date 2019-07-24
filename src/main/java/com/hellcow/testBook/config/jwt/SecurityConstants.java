package com.hellcow.testBook.config.jwt;

public class SecurityConstants {
	public static final String SECRET = "SecretKeyToGenJWTs";
	public static final long EXPIRATION_TIME = 3_600_000; // 1 hour
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String LOGIN = "/login**";
	public static final String USER_SIGN_UP = "/api/userService/save";
	//public static final String PASSWORD_CHANGE = "/password/**";
}
