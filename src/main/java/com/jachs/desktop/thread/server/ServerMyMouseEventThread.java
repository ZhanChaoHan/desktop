package com.jachs.desktop.thread.server;

import java.awt.event.InputEvent;
import java.io.ObjectInputStream;

import com.jachs.desktop.entity.MouseEventEn;
import com.jachs.desktop.thread.BaseThread;

/**
 * @author zhanchaohan
 * 
 */
public class ServerMyMouseEventThread extends BaseThread implements Runnable {
	private ObjectInputStream objectInputStream;
	public ServerMyMouseEventThread(ObjectInputStream objectInputStream) {
		super();
		this.objectInputStream = objectInputStream;
	}


	public void run () {
        try {
            MouseEventEn event;
        	while((event=(MouseEventEn) objectInputStream.readObject())!=null) {
        	    System.out.println ( event.getButton () );
        	   if(event.getButton ()==1) {//单击
        	       robot.mousePress(InputEvent.BUTTON1_MASK);
                   robot.mouseRelease(InputEvent.BUTTON1_MASK);
        	   }
        	   if(event.getButton ()==3) {//反击
        	       robot.mousePress(InputEvent.BUTTON3_MASK);
                   robot.mouseRelease(InputEvent.BUTTON3_MASK);
        	   }
        	}
        }catch (Exception e) {
            e.printStackTrace ();
        }
    }
}
