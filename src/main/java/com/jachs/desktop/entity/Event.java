package com.jachs.desktop.entity;

import java.io.Serializable;

/***
 * 
 * @author zhanchaohan
 *
 */
public abstract class Event implements Serializable{
	private static final long serialVersionUID = 1L;
	private SendInfoType sendInfoType;
	private char key;
	
	
	public Event(SendInfoType sendInfoType, char key) {
		super();
		this.sendInfoType = sendInfoType;
		this.key = key;
	}
	
	
	
}
