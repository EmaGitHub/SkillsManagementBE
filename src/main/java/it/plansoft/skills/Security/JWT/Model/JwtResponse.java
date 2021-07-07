package it.plansoft.skills.Security.JWT.Model;

import java.io.Serializable;
public class JwtResponse implements Serializable {
	
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final String username;
	
	public JwtResponse(String username, String jwttoken) {
		this.username = username;
		this.jwttoken = jwttoken;
	}
	public String getToken() {
		return this.jwttoken;
	}
	
	public String getUsername() {
		return this.username;
	}
}