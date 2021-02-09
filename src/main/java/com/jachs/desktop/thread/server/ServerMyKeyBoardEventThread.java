package com.jachs.desktop.thread.server;

import java.io.ObjectInputStream;

import com.jachs.desktop.entity.ClickEvent;
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
            ClickEvent event;
        	while((event=(ClickEvent) objectInputStream.readObject())!=null) {
        		System.out.println(event.getKey ());
        	}
        }catch (Exception e) {
            e.printStackTrace ();
        }
    }

}
