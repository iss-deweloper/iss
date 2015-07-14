/**
 *  Copyright (C) 2014,2015 Tomasz Bosak.
 **/
package pl.tobo.ISS.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import pl.tobo.ISS.entities.Screen;
import pl.tobo.ISS.entities.Tag;

public class ScreenDao {
	private static Logger logger = Logger.getLogger("pl.tobo.ISS");

	private EntityManager em;

	public ScreenDao(EntityManager em) {
		logger.entering("ScreenDao", "CONSTRUCTOR");
		this.em = em;
		logger.exiting("ScreenDao", "CONSTRUCTOR");
	}

	public Screen getById(int id) {
		logger.entering("ScreenDao", "getById", id);
		Screen s = em.find(Screen.class, id);
		if (s == null) {
			logger.warning("ERROR: Screen: " + id + " not found in DB.");
		}
		logger.exiting("ScreenDao", "getById", s);
		return s;
	}

	public Screen getByName(String name) {
		logger.entering("ScreenDao", "getByName", name);
		Screen screen = null;
		try {
			screen = (Screen) em
					.createQuery("SELECT u from Screen u WHERE u.name = :name")
					.setParameter("name", name).getSingleResult();
		} catch (NoResultException e) {
			logger.warning("No result exception for screen: " + name);
		}
		logger.exiting("ScreenDao", "getByName", screen);
		return screen;
	}

	public List<Screen> getAllScreens() {
		logger.entering("ScreenDao", "getAllScreens");
		List<Screen> screens = (List<Screen>) em.createQuery(
				"SELECT s FROM Screen s", Screen.class).getResultList();
		logger.exiting("ScreenDao", "getAllScreens", screens);
		return screens;
	}

	public boolean addScreen(Screen screen) {
		logger.entering("ScreenDao", "addScreen", screen);
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(screen);
			et.commit();
			logger.exiting("ScreenDao", "addScreen", true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			logger.exiting("ScreenDao", "addScreen", false);
			return false;
		}
	}

	public boolean addTagToScreen(int screenId, Tag t) {
		logger.entering("ScreenDao", "addTagToScreen", new Object[] { screenId,
				t });
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Screen s = em.find(Screen.class, screenId);
			if (!s.getTags().contains(t)) {
				s.getTags().add(t);
			}
			em.persist(s);
			et.commit();
			logger.exiting("ScreenDao", "addTagToScreen", true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			logger.exiting("ScreenDao", "addTagToScreen", false);
			return false;
		}

	}

	public boolean removeTagFromScreen(int screenId, Tag t) {
		logger.entering("ScreenDao", "removeTagFromScreen", new Object[] {
				screenId, t });
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Screen s = em.find(Screen.class, screenId);
			if (s.getTags().contains(t)) {
				s.getTags().remove(t);
			}
			em.persist(s);
			et.commit();
			logger.exiting("ScreenDao", "removeTagFromScreen", true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			logger.exiting("ScreenDao", "removeTagFromScreen", false);
			return false;
		}
	}
}
