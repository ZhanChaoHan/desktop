package com.jachs.desktop.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import com.jachs.desktop.configer.StaticConfigure;
import com.jachs.desktop.entity.MouseEventEn;
import com.jachs.desktop.entity.SendInfoType;

/***
 * 重新MouseListener实现自己逻辑
 * @author jachs
 *
 */
public class ClientMouseEvent implements MouseListener{
    MouseEventEn mouseEventEn=new MouseEventEn(SendInfoType.ClientMouse);
    
    
    //鼠标点击在mousePressed后触发,ID500
	public void mouseClicked(MouseEvent e) {
//	    System.out.println ("mouseClicked"+e.getButton () );
	    mouseEventEn.setId ( e.getID () );
	    mouseEventEn.setButton ( e.getButton () );
	    try {
            StaticConfigure.ClientMouseEventOos.writeObject ( mouseEventEn );
        }
        catch ( IOException e1 ) {
            e1.printStackTrace();
        }
	}
	//鼠标摁下,ID501
	public void mousePressed(MouseEvent e) {
//	    System.out.println ("mousePressed"+e.getButton () );
	    mouseEventEn.setId ( e.getID () );
	    mouseEventEn.setButton ( e.getButton () );
	    try {
            StaticConfigure.ClientMouseEventOos.writeObject ( mouseEventEn );
        }
        catch ( IOException e1 ) {
            e1.printStackTrace();
        }
	}
	//鼠标松开最后触发,ID502
	public void mouseReleased(MouseEvent e) {
//	    System.out.println ("mouseReleased"+e.getButton () );
	    mouseEventEn.setId ( e.getID () );
	    mouseEventEn.setButton ( e.getButton () );
	    try {
            StaticConfigure.ClientMouseEventOos.writeObject ( mouseEventEn );
        }
        catch ( IOException e1 ) {
            e1.printStackTrace();
        }
	}
	//鼠标移入
	public void mouseEntered(MouseEvent e) {
	}
	//鼠标移出
	public void mouseExited(MouseEvent e) {
	}

}
