package com.jachs.desktop.entity;

import java.io.Serializable;

public class Click implements Serializable{
	private int clickType;
	private boolean clickOnce;
	private int X;
	private int Y;
	
	
	public boolean isClickOnce() {
		return clickOnce;
	}
	public void setClickOnce(boolean clickOnce) {
		this.clickOnce = clickOnce;
	}
	public int getClickType() {
		return clickType;
	}
	public void setClickType(int clickType) {
		this.clickType = clickType;
	}
	public int getX() {
		return X;
	}
	public void setX(int x) {
		X = x;
	}
	public int getY() {
		return Y;
	}
	public void setY(int y) {
		Y = y;
	}
}
