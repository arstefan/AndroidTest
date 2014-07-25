package com.zinga.homecontrol.model;

import java.util.List;

public final class Floor {
	private Integer level;
	private String name;
	private String description;
	private List<Space> spaces;
	
	
	
	public List<Space> getSpaces() {
		return spaces;
	}
	public void setSpaces(List<Space> spaces) {
		this.spaces = spaces;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
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
		// TODO Auto-generated method stub
		return "Floor[name=" + this.name + ", description=" + this.description + ", level=" + this.level + " ]";
	}
	
}
