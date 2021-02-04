package com.jachs.desktop.entity;

import java.io.Serializable;

/***
 * 
 * @author zhanchaohan
 *
 */
public class KeyBoardEvent implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private SendInfoType sendInfoType=SendInfoType.KeyBoard;
	
	private char key;

	
	
	public KeyBoardEvent() {
		super();
	}

	public KeyBoardEvent(char key) {
		super();
		this.key = key;
	}

	public SendInfoType getSendInfoType() {
		return sendInfoType;
	}

	public void setSendInfoType(SendInfoType sendInfoType) {
		this.sendInfoType = sendInfoType;
	}

	public char getKey() {
		return key;
	}

	public void setKey(char key) {
		this.key = key;
	}
	
	
	
}
