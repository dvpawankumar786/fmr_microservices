package com.security.jwt.dao;

public class DropDownList { 
	private String value;
	private String name;
	
	
	public DropDownList(String value, String name) {
		super();
		this.value = value;
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
