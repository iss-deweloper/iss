/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import pl.tobo.ISS.entities.Content;
import pl.tobo.ISS.entities.Tag;

public class ContentDao {
	private EntityManager em;

	public ContentDao(EntityManager em) {
		this.em = em;
	}

	public Content getById(int id) {
		Content c = em.find(Content.class, id);
		if (c == null) {
			System.out.println("ERROR: Content: " + id + " not found in DB.");
		}
		return c;
	}
	
	public List<Content> getAllContents(){
		List<Content> contents = (List<Content>) em.createQuery("SELECT c FROM Content c", Content.class).getResultList();
		return contents;
	}
	
	public boolean addContent(Content content){
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(content);
			et.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			return false;
		}
	}
	
	public boolean addTagForContent(int contentId, Tag t){
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Content content = em.find(Content.class, contentId);
			if(!content.getTags().contains(t)){
				content.getTags().add(t);
			}
			em.persist(content);
			et.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			return false;
		}
		
	}
	public boolean removeTagFromContent(int screenId, Tag t){
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Content content = em.find(Content.class, screenId);
			if(content.getTags().contains(t)){
				content.getTags().remove(t);
			}
			em.persist(content);
			et.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			return false;
		}
	}

	public boolean removeById(int id) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Content content = em.find(Content.class, id);
			em.remove(content);
			em.flush();
			et.commit();
			return true;
		}catch (Exception e){
			e.printStackTrace();
			et.rollback();
			return false;
		}
	}
	
	
	
}
