package it.plansoft.skills.Security.JWT.Model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtResponse implements Serializable {
	
	private static final long serialVersionUID = -8091879091924046844L;
	private final String token;
	private final String username;
	private final Long id;
	private final int status;
	
	public JwtResponse(String username, Long userId, String jwttoken, int status) {
		this.username = username;
		this.id = userId;
		this.token = jwttoken;
		this.status = status;
	}
}