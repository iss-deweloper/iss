/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Type")
public class Type {
	
	@Id
	@GeneratedValue
	@Column(name="typeId")
	private int id;
	
	@Column(name="typeDescription")
	@Enumerated(EnumType.STRING)
	private TypeDescription description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TypeDescription getDescription() {
		return description;
	}

	public void setDescription(TypeDescription description) {
		this.description = description;
	}
	
	
}
