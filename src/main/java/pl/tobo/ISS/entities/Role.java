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
@Table(name="Role")
public class Role {
	@Id
	@GeneratedValue
	@Column(name="roleId")
	private int id;
	
	@Column(name="roleDescription")
	@Enumerated(EnumType.STRING)
	private RoleDescription description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RoleDescription getDescription() {
		return description;
	}

	public void setDescription(RoleDescription description) {
		this.description = description;
	}
	
	
}
