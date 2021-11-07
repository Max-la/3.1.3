package com.example.spring_boot.Dao;

import com.example.spring_boot.Model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<User> getAllUser() {
		return entityManager.createQuery("from User",User.class).getResultList();
	}

	@Override
	public void add(User user) {
		entityManager.persist(user);
	}

	@Override
	public User getUser(Long id) {
		return  entityManager.find(User.class,id);
	}

	@Override
	public void delete(Long id) {
		entityManager.createQuery("delete from User  where id = :id ").setParameter("id",id).executeUpdate();
	}

	@Override
	public void updateUser(User user) {
		 entityManager.merge(user);
	}

	@Override
	public User getUserByName(String name) {
		try {
			return entityManager.createQuery("select u from User u inner join fetch u.roles where u.name = :name", User.class)
					.setParameter("name", name).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
