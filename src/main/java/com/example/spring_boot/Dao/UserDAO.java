package com.example.spring_boot.Dao;



import com.example.spring_boot.Model.User;

import java.util.List;

public interface UserDAO {
	 List<User> getAllUser();
	 void add(User user);
	 User getUser(Long id);
	 void delete(Long id);
	 void updateUser(User user);
	 User getUserByName(String name);
}
