package com.example.demo.Security;

import java.util.Set;

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
	
}
