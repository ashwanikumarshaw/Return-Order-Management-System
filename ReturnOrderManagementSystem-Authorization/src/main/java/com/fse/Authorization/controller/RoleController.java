package com.fse.Authorization.controller;

import com.fse.Authorization.entity.Role;
import com.fse.Authorization.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping({ "/createNewRole" })
	@PreAuthorize("hasRole('Admin')")
	public Role createNewRole(@RequestBody Role role) {
		return roleService.createNewRole(role);
	}
}
