package com.example.spring_boot.Servise;

import com.example.spring_boot.Dao.RoleDao;
import com.example.spring_boot.Model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	public final RoleDao roleDao;

	@Autowired
	public RoleServiceImpl(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public List<Role> getRole() {
		return roleDao.getRole();
	}

	@Override
	public void add(Role role) {
		roleDao.add(role);
	}

	@Override
	public void updateRole(Role role) {
		roleDao.updateRole(role);
	}

	@Override
	public void delete(Long id) {
		roleDao.delete(id);
	}

	@Override
	public Role getRoleByName(String name) {
		return roleDao.getRoleByName(name);
	}
}
