/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import pl.tobo.ISS.entities.Screen;
import pl.tobo.ISS.entities.Tag;

public class ScreenDao {
	private EntityManager em;

	public ScreenDao(EntityManager em) {
		this.em = em;
	}

	public Screen getById(int id) {
		Screen s = em.find(Screen.class, id);
		if (s == null) {
			System.out.println("ERROR: Screen: " + id + " not found in DB.");
		}
		return s;
	}
	
	public Screen getByName(String name) {
		Screen screen = null;
		try {
			screen = (Screen) em
					.createQuery("SELECT u from Screen u WHERE u.name = :name")
					.setParameter("name", name).getSingleResult();
		} catch (NoResultException e) {
			System.out.println("No result exception for screen: " + name);
		}
		return screen;
	}
	
	
	public List<Screen> getAllScreens(){
		List<Screen> Screens = (List<Screen>) em.createQuery("SELECT s FROM Screen s", Screen.class).getResultList();
		return Screens;
	}
	
	public boolean addScreen(Screen screen){
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(screen);
			et.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			return false;
		}
	}
	
	public boolean addTagToScreen(int screenId, Tag t){
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Screen s = em.find(Screen.class, screenId);
			if(!s.getTags().contains(t)){
				s.getTags().add(t);
			}
			em.persist(s);
			et.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			return false;
		}
		
	}
	public boolean removeTagFromScreen(int screenId, Tag t){
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Screen s = em.find(Screen.class, screenId);
			if(s.getTags().contains(t)){
				s.getTags().remove(t);
			}
			em.persist(s);
			et.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			return false;
		}
	}
}
