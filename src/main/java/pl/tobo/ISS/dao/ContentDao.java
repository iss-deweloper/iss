/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import pl.tobo.ISS.entities.Content;
import pl.tobo.ISS.entities.Tag;

public class ContentDao {
	
	private static Logger logger = Logger.getLogger("pl.tobo.ISS");

	private EntityManager em;
	
	public ContentDao(EntityManager em) {
		logger.entering("ContentDao", "CONSTRUCTOR");
		this.em = em;
		logger.exiting("ContentDao", "CONSTRUCTOR");
	}

	public Content getById(int id) {
		logger.entering("ContentDao", "getById",id);
		Content c = em.find(Content.class, id);
		if (c == null) {
			logger.warning("ERROR: Content: " + id + " not found in DB.");
		}
		logger.exiting("ContentDao", "getById",c);
		return c;
	}
	
	public List<Content> getAllContents(){
		logger.entering("ContentDao", "getAllContents");
		List<Content> contents = (List<Content>) em.createQuery("SELECT c FROM Content c", Content.class).getResultList();
		logger.exiting("ContentDao", "getAllContents", contents); 
		return contents;
	}
	
	public boolean addContent(Content content){
		logger.entering("ContentDao", "addContent",content);
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(content);
			et.commit();
			logger.exiting("ContentDao", "addContent", true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			logger.exiting("ContentDao", "addContent", false);
			return false;
		}
	}
	
	public boolean addTagForContent(int contentId, Tag t){
		logger.entering("ContentDao", "addTagForContent",new Object[]{contentId,t});
		
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Content content = em.find(Content.class, contentId);
			if(!content.getTags().contains(t)){
				content.getTags().add(t);
			}
			em.persist(content);
			et.commit();
			logger.exiting("ContentDao", "addTagForContent", true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			logger.exiting("ContentDao", "addTagForContent", false);
			return false;
		}
		
	}
	public boolean removeTagFromContent(int screenId, Tag t){
		logger.entering("ContentDao", "removeTagFromContent",new Object[]{screenId,t});
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Content content = em.find(Content.class, screenId);
			if(content.getTags().contains(t)){
				content.getTags().remove(t);
			}
			em.persist(content);
			et.commit();
			logger.exiting("ContentDao", "removeTagFromContent", true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			logger.exiting("ContentDao", "removeTagFromContent", false);
			return false;
		}
	}

	public boolean removeById(int id) {
		logger.entering("ContentDao", "removeById",id);
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Content content = em.find(Content.class, id);
			em.remove(content);
			em.flush();
			et.commit();
			logger.exiting("ContentDao", "removeById", true);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			et.rollback();
			logger.exiting("ContentDao", "removeById", false);
			return false;
		}
	}
}
