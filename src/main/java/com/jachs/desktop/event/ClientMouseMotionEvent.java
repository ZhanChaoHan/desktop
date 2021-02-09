package com.jachs.desktop.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import com.jachs.desktop.configer.StaticConfigure;
import com.jachs.desktop.entity.MouseMotionEventEn;
import com.jachs.desktop.entity.SendInfoType;

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
	    MouseMotionEventEn mouseEvent=new MouseMotionEventEn(SendInfoType.ClientMouseMotion,e.getX (),e.getY ());
	    
	    try {
            StaticConfigure.ClientMouseMotionEventOos.writeObject ( mouseEvent );
        }
        catch ( IOException e1 ) {
            e1.printStackTrace();
        }
//		System.out.println(e.getID()+"鼠标移动事件");
	}

}
