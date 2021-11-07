package com.example.spring_boot.Servise;

import com.example.spring_boot.Model.Role;
import com.example.spring_boot.Model.User;

import java.util.List;

public interface RoleService {
	List<Role> getAllRoles();
	List<Role> getRole();
	void add(Role role);
	void updateRole(Role role);
	void delete(Long id);
	Role getRoleByName(String name);
}
