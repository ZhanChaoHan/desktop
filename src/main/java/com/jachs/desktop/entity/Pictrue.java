package com.jachs.desktop.entity;

import java.io.Serializable;
/****
 * 圖片對象
 * @author Jachs
 *
 */
public class Pictrue implements Serializable{
	private int size;
	private byte[] data;
	public Pictrue() {
		super();
	}
	public Pictrue(int size, byte[] data) {
		super();
		this.size = size;
		this.data = data;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
}
