package com.example.demo.Security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum ApplicationUserPermission {
	
	WRITE("WRITE"),
	READ("READ");
	
	private final String permission;
	
	ApplicationUserPermission(String permission) {
		this.permission = permission;
	}
	
	public String getPermission() {
		return this.permission;
	}
	
	public static Set<ApplicationUserPermission> getUserPermission() {
		return new HashSet<ApplicationUserPermission>(Arrays.asList(READ));
	}
	
	public static Set<ApplicationUserPermission> getAdminPermission() {
		return new HashSet<ApplicationUserPermission>(Arrays.asList(WRITE, READ));
	}

}
