/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Tag")
public class Tag {
	@Id
	@GeneratedValue
	@Column(name="tagId")
	private int id;

	@Column(name="tagName")
	private String name;
	@Column(name="tagDescription")
	private String description;
	
	@ManyToMany(mappedBy="tags")	
	private Set<Content> contents;
	
	@ManyToMany(mappedBy="tags")
	private Set<Screen> screens;
	
	public Set<Content> getContents() {
		return contents;
	}
	public void setContents(Set<Content> contents) {
		this.contents = contents;
	}
	public Set<Screen> getScreens() {
		return screens;
	}
	public void setScreens(Set<Screen> screens) {
		this.screens = screens;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + ", description="
				+ description + ", #contents=" + contents.size() + ", #screens="
				+ screens.size() + "]";
	}
	
	
	
}
