package com.jachs.desktop.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/****
 * 
 * @author zhanchaohan
 *
 */
public class MyMouseMotionEvent implements MouseMotionListener,Runnable{
	
	//鼠标拖动事件
	public void mouseDragged(MouseEvent e) {
		System.out.println(e.getID()+"鼠标拖动事件");
	}
	//鼠标移动事件
	public void mouseMoved(MouseEvent e) {
		System.out.println(e.getID()+"鼠标移动事件");
	}
	public void run() {
		
	}

}
