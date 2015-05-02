/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Settings")
public class GlobalSetting {
	@Id
	@Column(name="settingKey")
	private String key;
	
	@Column(name="settingValue")
	private String value;
	@Column(name="settingType")
	private SettingType type;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public SettingType getType() {
		return type;
	}
	public void setType(SettingType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "GlobalSetting of type: "+ type+" key = "+key+" value = "+value +"  ";
	}
}
