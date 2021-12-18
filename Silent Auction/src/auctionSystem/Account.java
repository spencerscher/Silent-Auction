package auctionSystem;

import Security.*;
//import java.security.SecureRandom;
//import java.security.spec.InvalidKeySpecException;
//import java.util.Arrays;
//import java.util.Base64;
//import java.util.Random;
//import javax.crypto.SecretKeyFactory;
//import javax.crypto.spec.PBEKeySpec;

import Security.PasswordUtils;

public class Account {
	
	private String username;
	private String password;
	private String salt;
	private String securePassword; 
	
	//create an account for a user with all necessary credentials.
	public Account(){
		username = "";
		password = "";
		securePassword = "";
		salt = "";
	}
	
	//salt value is used to hash the raw password.
	public Account(String username, String password){
		this.username = username;
		this.salt = PasswordUtils.getSalt(30);
		this.securePassword = PasswordUtils.generateSecurePassword(password, salt);
	}
	
	//getters and setters for passwords and username.
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getSalt() {
		return salt;
	}
	
	//return password hashed with MD-5 encryption (not as secure as it could be, will update to SHA-256 soon).
	public String getSecurePassword() {
		return securePassword;
	}
	
//	public String decryptPassword(String securePassword, String salt) {
//		
//	}

	
}
