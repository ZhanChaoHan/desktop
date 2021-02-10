package com.jachs.desktop.event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import com.jachs.desktop.configer.StaticConfigure;
import com.jachs.desktop.entity.ClickEventEn;
import com.jachs.desktop.entity.Event;
import com.jachs.desktop.entity.SendInfoType;

/***
 * 
 * @author zhanchaohan
 *
 */
public class ClientKeyBoardEvent implements KeyListener{
	//第二执行
	public void keyTyped(KeyEvent e) {
	    System.out.println ("keyTyped:"+ e.getID ()+"\t"+e.getKeyCode ()+"\t"+e.getWhen () );
	}
	//按下一个键盘事件,最先触发
	public void keyPressed(KeyEvent e) {
	    System.out.println ("keyPressed:"+ e.getID ()+"\t"+e.getKeyCode ()+"\t"+e.getWhen () );
	}
	//最后释放键盘事件，最后触发
	public void keyReleased(KeyEvent e) {
	    System.out.println ("keyReleased:"+ e.getID ()+"\t"+e.getKeyCode ()+"\t"+e.getWhen () );
		System.out.println(e.getKeyChar()+"keyReleased");
		Event keyBoardEvent=new ClickEventEn(SendInfoType.KeyBoard,e.getKeyChar());
		try {
			StaticConfigure.ClientKeyBoardEventOos.writeObject(keyBoardEvent);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
