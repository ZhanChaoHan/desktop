package com.jachs.desktop.event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/***
 * 
 * @author zhanchaohan
 *
 */
public class MyKeyBoardEvent implements KeyListener,Runnable{

	public void keyTyped(KeyEvent e) {
		System.out.println(e.getKeyChar()+"keyTyped");
	}

	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyChar()+"keyPressed");
	}

	public void keyReleased(KeyEvent e) {
		System.out.println(e.getKeyChar()+"keyReleased");
	}

	public void run() {
		
	}

}
