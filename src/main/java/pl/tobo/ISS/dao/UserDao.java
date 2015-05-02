/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import pl.tobo.ISS.entities.Role;
import pl.tobo.ISS.entities.RoleDescription;
import pl.tobo.ISS.entities.Type;
import pl.tobo.ISS.entities.TypeDescription;
import pl.tobo.ISS.entities.User;
import pl.tobo.ISS.utils.Utils;

public class UserDao {
	private EntityManager em;

	public UserDao(EntityManager em) {
		this.em = em;
	}

	public User getById(int id) {
		User u = em.find(User.class, id);
		if (u == null) {
			System.out.println("ERROR: User: " + id + " not found in DB.");
		}
		return u;
	}

	public User getByLogin(String login) {
		User u = null;
		try {
			u = (User) em
					.createQuery("SELECT u from User u WHERE u.login = :login")
					.setParameter("login", login).getSingleResult();
		} catch (NoResultException e) {
			System.out.println("No result exception for user: " + login);
		}catch(PersistenceException ex){
			System.out.println("Persistence exception: "+ex+" returning null");
		}
		return u;
	}

	/**
	 * Assumed: user password is in "clear text" - needs to be hashed user role
	 * is not set
	 * 
	 * @param u
	 * @return
	 */
	public boolean addUser(User u) {

		assert (u.getRole() == null);
		assert (u.getPassword().length() < 32);

		u.setPassword(Utils.getMD5(u.getPassword()));

		Role r = new Role();
		r.setDescription(RoleDescription.USER);
		u.setRole(r);

		Type t = new Type();
		t.setDescription(TypeDescription.LOCAL);
		u.setType(t);
		
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(r);
			em.persist(t);
		    em.persist(u);
			et.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			return false;
		}
	}

}
