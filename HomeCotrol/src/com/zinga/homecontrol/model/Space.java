package com.zinga.homecontrol.model;


/*
 * Pomieszczenie:
 * 	- nazwa (i.e. POK1)
 *  - opis (i.e. Pokój dzienny)
 *  - piêtro (i.e. Parter, Piêtro 1, Strych)
 */
public final class Space {
	private String name;
	private String description;
	private Floor floor;
	
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
	public Floor getFloor() {
		return floor;
	}
	public void setFloor(Floor floor) {
		this.floor = floor;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Space [name=" + this.name + ", description=" + this.description + ", floor=" + floor.toString() + " ]";
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return super.equals(o);
	}
	
}
