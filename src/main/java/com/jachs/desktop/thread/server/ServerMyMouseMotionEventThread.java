package com.jachs.desktop.thread.server;

import java.io.ObjectInputStream;
import java.net.ServerSocket;

import com.jachs.desktop.entity.Event;
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
        	Event event;
        	while((event=(Event) objectInputStream.readObject())!=null) {
        		System.out.println(event.getSendInfoType().name());
        	}
        }catch (Exception e) {
            e.printStackTrace ();
        }
    }

}
