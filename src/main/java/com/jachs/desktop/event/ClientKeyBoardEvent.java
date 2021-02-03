package com.jachs.desktop.event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ObjectOutputStream;

/***
 * 
 * @author zhanchaohan
 *
 */
public class ClientKeyBoardEvent implements KeyListener,Runnable{
	private String siP;
	private int sPort;
	
	ObjectOutputStream oos;
	
	public ClientKeyBoardEvent() {
		super();
	}

	public ClientKeyBoardEvent(String siP, int sPort) {
		super();
		this.siP = siP;
		this.sPort = sPort;
	}
	//第二执行
	public void keyTyped(KeyEvent e) {
	}
	//按下一个键盘事件,最先触发
	public void keyPressed(KeyEvent e) {
	}
	//最后释放键盘事件，最后触发
	public void keyReleased(KeyEvent e) {
		System.out.println(e.getKeyChar()+"keyReleased");
	}

	public void run() {
		
	}

}
