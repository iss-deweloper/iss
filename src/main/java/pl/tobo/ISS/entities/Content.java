/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.entities;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Content")
public class Content {
	
	@Id
	@GeneratedValue
	@Column(name="contentId")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="contentUserId")	
	private User user;
	
	@Column(name="contentAddress")		
	private String contentAddress;

    @Column(name="contentValidFrom")	
    private Timestamp validFrom;
    
    @Column(name="contentValidTo")	
    private Timestamp validTo;
    
    @Column(name="contentTitle")
	private String title;
    
    @Column(name="contentType")
    private ContentType contentType;
    
	@ManyToMany
    @JoinTable(name="contenttag",
    		joinColumns=@JoinColumn(name="contentId"),
    		inverseJoinColumns=@JoinColumn(name="tagId"))
    private Set<Tag> tags;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContentAddress() {
		return contentAddress;
	}

	public void setContentAddress(String contentAddress) {
		this.contentAddress = contentAddress;
	}

	public Timestamp getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Timestamp validFrom) {
		this.validFrom = validFrom;
	}

	public Timestamp getValidTo() {
		return validTo;
	}

	public void setValidTo(Timestamp validTo) {
		this.validTo = validTo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
    public ContentType getContentType() {
		return contentType;
	}

	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}

	public Set<Tag> getTags() {
		if (tags==null){
			tags=new HashSet<Tag>();
		}
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}


	@Override
	public String toString() {
		StringBuilder description = new StringBuilder();
		description.append("Content [");
		description.append("id= " + id+", ");
		description.append("user= " + user + ", ");
		description.append("contentAddress= " + contentAddress + ", ");
		description.append("validFrom= " + validFrom + ", ");
		description.append("validTo= " + validTo + ", ");
		description.append("title= " + title + ", ");
		description.append("contentType= " + contentType + ", ");
		if(tags!=null){
			description.append("#tags= " + tags.size());
		}else{
			description.append("no tags yet");
		}
		description.append("]");
		return description.toString();
	}
}
