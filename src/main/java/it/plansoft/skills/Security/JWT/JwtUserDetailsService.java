package it.plansoft.skills.Security.JWT;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.plansoft.skills.DTO.UserDTO;
import it.plansoft.skills.Repository.UserDAO;
@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	private UserDAO userDAO;
	
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	public JwtUserDetailsService(UserDAO userDAO, PasswordEncoder bcryptEncoder) {
		this.userDAO = userDAO;
		this.bcryptEncoder = bcryptEncoder;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDTO user = userDAO.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		// if System Admin
		if (user.getIsSystemAdmin()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_SYSTEM_ADMIN"));
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}
	
	public UserDTO save(UserDTO user) {
		UserDTO newUser = new UserDTO();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setDtInsert(new java.util.Date());
		newUser.setIsSystemAdmin(user.getIsSystemAdmin());
		return userDAO.save(newUser);
	}
}