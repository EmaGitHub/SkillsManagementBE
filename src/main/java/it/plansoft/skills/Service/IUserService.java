package it.plansoft.skills.Service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public interface IUserService {

	/**
	 *  retrieve roles 
	 * @param sso
	 * @return
	 */
	String getRoles(String sso);
	
	/**
	 * retrieve authorities
	 * @param sso
	 * @return
	 */
	List<GrantedAuthority> getAuthorities(String sso);
}
