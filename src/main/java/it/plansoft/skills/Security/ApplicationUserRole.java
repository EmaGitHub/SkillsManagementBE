package it.plansoft.skills.Security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum ApplicationUserRole {
	
	USER(ApplicationUserPermission.getUserPermission()),
	ADMIN(ApplicationUserPermission.getAdminPermission());
	
	private final Set<ApplicationUserPermission> permissions;
	
	ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;
	}
	
	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}
	
//	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
//		getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//			.collect(Collectors.toSet())
//		permissio
//		return new SimpleGrantedAuthority("ROLE_ADMIN");
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
//	}
	
}
