package com.prasenjit.examples.test.clients;
/**
 * 
 * @author prasenjit
 *
 */
public class Profile {
	
	private boolean isOnline;
	private String name;
	
	public Profile() {
		super();
		
	}
	public Profile(boolean isOnline, String name) {
		super();
		this.isOnline = isOnline;
		this.name = name;
	}
	public boolean isOnline() {
		return isOnline;
	}
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Profile [isOnline=" + isOnline + ", name=" + name + "]";
	}
	
}
