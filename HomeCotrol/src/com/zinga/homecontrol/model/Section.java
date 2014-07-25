package com.zinga.homecontrol.model;

import java.util.ArrayList;

public final class Section {
	private String title;
	private ArrayList<Item> items;
	
	public static Section getInstance(Section section){
		Section copy = new Section();
		copy.title = section.title;
		if((section.items != null) && !section.items.isEmpty()){
			copy.items = new ArrayList<Item>(section.items.size());
			for(Item item: section.items){
				copy.items.add(Item.getInstance(item));
			}
		} else {
			copy.items = new ArrayList<Item>();
		}
		return copy;
 }
 
 @Override
 public String toString() {
   if (this.title != null) {
	  return this.title;
   }
   return "NULL";
 }	
 
 public Section(){
	 this.items = new ArrayList<Item>();
 }
 
 public ArrayList<Item> getItems() {
	return items;
 }
 
 public void setItems(ArrayList<Item> items) {
	this.items = items;
 }
 
 public String getTitle() {
	return title;
 }
 
 public void setTitle(String title) {
	this.title = title;
 }
 
 @Override
 public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.items == null) ? 0 : this.items.hashCode());
    result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
    return result;
 }

 @Override
 public boolean equals(Object obj) {
    if (this == obj) {
       return true;
    }
    if (obj == null) {
       return false;
    }
    if (getClass() != obj.getClass()) {
       return false;
    }
    Section other = (Section) obj;
    if (this.items == null) {
       if (other.items != null) {
          return false;
       }
    } else if (!this.items.equals(other.items)) {
       return false;
    }
    if (this.title == null) {
       if (other.title != null) {
          return false;
       }
    } else if (!this.title.equals(other.title)) {
       return false;
    }
    return true;
  }
	
}
