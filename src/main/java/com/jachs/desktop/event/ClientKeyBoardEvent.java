package com.jachs.desktop.event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.jachs.desktop.entity.KeyBoardEvent;

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
		KeyBoardEvent keyBoardEvent=new KeyBoardEvent(e.getKeyChar());
		try {
			oos.writeObject(keyBoardEvent);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void run() {
		try {
			Socket socket=new Socket(siP, sPort);
			oos=new ObjectOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
