package com.jachs.desktop.thread.server;

import java.io.ObjectInputStream;

import com.jachs.desktop.entity.Event;
import com.jachs.desktop.thread.BaseThread;

/**
 * @author zhanchaohan
 * 
 */
public class ServerMyKeyBoardEventThread extends BaseThread implements Runnable {
	private ObjectInputStream objectInputStream;
	public ServerMyKeyBoardEventThread(ObjectInputStream objectInputStream) {
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
