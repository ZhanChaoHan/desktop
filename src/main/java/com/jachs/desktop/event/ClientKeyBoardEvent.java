package com.jachs.desktop.event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import com.jachs.desktop.configer.StaticConfigure;
import com.jachs.desktop.entity.ClickEvent;
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
	}
	//按下一个键盘事件,最先触发
	public void keyPressed(KeyEvent e) {
	}
	//最后释放键盘事件，最后触发
	public void keyReleased(KeyEvent e) {
		System.out.println(e.getKeyChar()+"keyReleased");
		Event keyBoardEvent=new ClickEvent(SendInfoType.KeyBoard,e.getKeyChar());
		try {
			StaticConfigure.ClientKeyBoardEventOos.writeObject(keyBoardEvent);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
