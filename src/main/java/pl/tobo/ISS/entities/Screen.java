/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

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
	private static Logger logger = Logger.getLogger("pl.tobo.ISS");  

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
		if(tags == null){
			logger.severe("ceating new tag HashSet in content "+ id+
					"this should not happen!");
			tags = new HashSet<Tag>();
		}
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

	@Override
	public String toString() {
		
		StringBuilder description = new StringBuilder();
		description.append("Screen [");
		description.append("id= " + id+", ");
		description.append("name= " + name + ", ");
		description.append("description= " + description + ", ");
		description.append("format= " + format + ", ");
		
	
		if(tags!=null){
			description.append("#tags= " + tags.size());
		}else{
			description.append("no tags yet");
		}
		description.append("]");
		return description.toString();
		}
	

}
