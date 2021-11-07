package com.example.spring_boot.Servise;

import com.example.spring_boot.Dao.UserDAOImpl;
import com.example.spring_boot.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	private final UserDAOImpl userDAO;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserDAOImpl userDAO, PasswordEncoder passwordEncoder) {
		this.userDAO = userDAO;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<User> getAllUser() {
		return userDAO.getAllUser();
	}

	@Override
	public void add(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDAO.add(user);
	}

	@Override
	public User getUser(Long id) {
		return userDAO.getUser(id);
	}

	@Override
	public void delete(Long id) {
		userDAO.delete(id);
	}

	@Override
	public void updateUser(User user) {
		if (Objects.equals(user.getPassword(), "")){
			user.setPassword(userDAO.getUser(user.getId()).getPassword());
		}else {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		userDAO.updateUser(user);
	}

	@Override
	public User getUserByName(String name) {
		return userDAO.getUserByName(name);
	}
}
