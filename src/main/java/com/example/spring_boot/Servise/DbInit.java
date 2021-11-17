package com.example.spring_boot.Servise;

import com.example.spring_boot.Model.Role;
import com.example.spring_boot.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashSet;

@Configuration
@Transactional
public class DbInit{

	public final UserService userService;
	private final RoleService roleService;

	@Autowired
	public DbInit(@Qualifier("userServiceImpl") UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}

	@PostConstruct
	public void Init(){
		HashSet<Role> Adminrole = new HashSet<>();
		HashSet<Role> Userrole = new HashSet<>();

		Role role = new Role("ADMIN");
		User userAdmin = new User("admin1","admin","admin");
		Adminrole.add(new Role(1L,"ADMIN"));
		userAdmin.setRoles(Adminrole);
		roleService.add(role);
		userService.add(userAdmin);

		Role role1 = new Role("USER");
		User user = new User("user1","user","user");
		Userrole.add(new Role(2L,"USER"));
		user.setRoles(Userrole);
		roleService.add(role1);
		userService.add(user);
	}
}
