package com.jachs.desktop.entity;

import java.io.Serializable;
import java.util.TreeSet;

public class ClickKeyEntity implements Serializable{
	private TreeSet<Integer> clickKey;

	public TreeSet<Integer> getClickKey() {
		return clickKey;
	}
	public void setClickKey(TreeSet<Integer> clickKey) {
		this.clickKey = clickKey;
	}
}
