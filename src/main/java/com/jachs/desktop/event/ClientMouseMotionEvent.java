package com.jachs.desktop.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/****
 * 
 * @author zhanchaohan
 *
 */
public class ClientMouseMotionEvent implements MouseMotionListener,Runnable{
	private String siP;
	private int sPort;
	
	public ClientMouseMotionEvent() {
		super();
	}
	
	
	public ClientMouseMotionEvent(String siP, int sPort) {
		super();
		this.siP = siP;
		this.sPort = sPort;
	}

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
