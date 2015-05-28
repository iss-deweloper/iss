/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="Screen")
public class Screen {

	@Id
	@GeneratedValue
	@Column(name="screenId")
	private int id;

	@Column(name="screenName")
	private String name;
	@Column(name="screenDescription")
	private String description;
	
	@ManyToMany( cascade=CascadeType.PERSIST)
	@JoinTable(name="screentag",
		joinColumns=@JoinColumn(name="screenId"),
		inverseJoinColumns=@JoinColumn(name="tagId"))
	@OrderBy("name")
	private Set<Tag> tags;

	
	@Column(name="screenFormat")
	private String format;
	
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

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}


}
