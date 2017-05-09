package org.ms.dao;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.ms.entity.User;

/**
 * Created on 05.04.2017 22:12:08
 * 
 * @author Hubert Popio³kiewicz
 */
@Stateless
public class UserDAO extends AbstractDAO<User> {

	public boolean exists(String login, String password) {
		return getSingle(login, password) != null;
	}

	public User getSingle(String login, String password) {
		Query query = entityManager
				.createQuery(
						"from " + entityClass.getSimpleName() + " where login = :login and password_hash = :password")
				.setParameter("login", login).setParameter("password", password);
		return getSingleResult(query);
	}
}
