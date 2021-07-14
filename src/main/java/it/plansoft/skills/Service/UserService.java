package it.plansoft.skills.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import it.plansoft.skills.DTO.UserDTO;
import it.plansoft.skills.Repository.UserDAO;

@Service
public class UserService extends BaseCrudService<JpaRepository<UserDTO, Long>, UserDTO, Long> implements IUserService {

	public UserService(JpaRepository<UserDTO, Long> repo) {
		super(repo);
	}

	@Override
	public String getRoles(String sso) {
		UserDTO user = ((UserDAO)repo).findByUsername(sso);
		if (user.getIsSystemAdmin()) {
			return "SYSTEM_ADMIN";
		}
		return "";
	}

	@Override
	public List<GrantedAuthority> getAuthorities(String sso) {
		UserDTO user = ((UserDAO)repo).findByUsername(sso);
		List<GrantedAuthority> authorities = new ArrayList<>();	
		// if System Admin
		if (user.getIsSystemAdmin()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_SYSTEM_ADMIN"));
		}
		return authorities;
	}

}