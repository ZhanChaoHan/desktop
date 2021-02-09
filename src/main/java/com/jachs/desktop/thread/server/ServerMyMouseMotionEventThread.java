package com.jachs.desktop.thread.server;

import java.io.ObjectInputStream;

import com.jachs.desktop.entity.MouseMotionEventEn;
import com.jachs.desktop.thread.BaseThread;

/**
 * @author zhanchaohan
 * 
 */
public class ServerMyMouseMotionEventThread extends BaseThread implements Runnable {
	private ObjectInputStream objectInputStream;
	
	public ServerMyMouseMotionEventThread(ObjectInputStream objectInputStream) {
		super();
		this.objectInputStream = objectInputStream;
	}


	public void run () {
        try {
            MouseMotionEventEn event;
        	while((event=(MouseMotionEventEn) objectInputStream.readObject())!=null) {
        		this.robot.mouseMove ( event.getX (), event.getY () );
        	}
        }catch (Exception e) {
            e.printStackTrace ();
        }
    }

}
