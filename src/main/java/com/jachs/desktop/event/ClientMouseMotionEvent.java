package com.jachs.desktop.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.jachs.desktop.entity.MouseMotionEventEn;

/****
 * 
 * @author zhanchaohan
 *
 */
public class ClientMouseMotionEvent implements MouseMotionListener{
	//鼠标拖动事件
	public void mouseDragged(MouseEvent e) {
//		System.out.println(e.getID()+"鼠标拖动事件");
	}
	//鼠标移动事件
	public void mouseMoved(MouseEvent e) {
	    MouseMotionEventEn mouseEvent=new MouseMotionEventEn();
	    
	    System.out.println (  e.getPoint ().x );
	    System.out.println (  e.getPoint ().y );
	    System.out.println ( e.getX () );
	    System.out.println ( e.getY () );
	    System.out.println ( "--------------" );
//		System.out.println(e.getID()+"鼠标移动事件");
	}

}
