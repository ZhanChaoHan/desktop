package com.jachs.desktop.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/***
 * 重新MouseListener实现自己逻辑
 * @author jachs
 *
 */
public class ClientMouseEvent implements MouseListener,Runnable{
	private String siP;
	private int sPort;
	
	public ClientMouseEvent() {
		super();
	}
	public ClientMouseEvent(String siP, int sPort) {
		super();
		this.siP = siP;
		this.sPort = sPort;
	}


	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void run() {
		// TODO Auto-generated method stub
		
	}



}
