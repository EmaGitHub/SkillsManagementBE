package it.plansoft.skills.Security.JWT;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.plansoft.skills.DTO.RoleDTO;
import it.plansoft.skills.DTO.UserDTO;
import it.plansoft.skills.Repository.UserDAO;
import it.plansoft.skills.Repository.UserRolesDAO;
import it.plansoft.skills.Repository.Roles.RolesDAO;
@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	private UserDAO userDAO;
	private RolesDAO rolesDAO;
	
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	public JwtUserDetailsService(UserDAO userDAO, RolesDAO rolesDAO, PasswordEncoder bcryptEncoder) {
		this.userDAO = userDAO;
		this.rolesDAO = rolesDAO;
		this.bcryptEncoder = bcryptEncoder;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDTO user = userDAO.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
		// Check Roles		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		Set<RoleDTO> roles;
		try {
			roles = rolesDAO.getUserRoles(user.getId());
	        for (RoleDTO role : roles) {
	            authorities.add(new SimpleGrantedAuthority(role.getName()));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
         
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}
	
	public UserDTO save(UserDTO user) {
		UserDTO newUser = new UserDTO();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setDtInsert(LocalDate.now());
		return userDAO.save(newUser);
	}
}