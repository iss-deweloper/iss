/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import pl.tobo.ISS.entities.Tag;

public class TagDao {
	private EntityManager em;

	public TagDao(EntityManager em) {
		this.em = em;
	}

	public Tag getById(int id) {
		Tag t = em.find(Tag.class, id);
		if (t == null) {
			System.out.println("ERROR: Tag: " + id + " not found in DB.");
		}
		return t;
	}
	
	public List<Tag> getAllTags(){
		List<Tag> tags = (List<Tag>) em.createQuery("SELECT t FROM Tag t",Tag.class).getResultList();
		return tags;
	}
	
	public boolean addTag(Tag tag){
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(tag);
			et.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			return false;
		}
	}
		
	
}
