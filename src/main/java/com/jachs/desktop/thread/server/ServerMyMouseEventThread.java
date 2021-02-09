package com.jachs.desktop.thread.server;

import java.io.ObjectInputStream;

import com.jachs.desktop.entity.Event;
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
//            MouseEventEn event;
//        	while((event=(MouseEventEn) objectInputStream.readObject())!=null) {
//        		System.out.println(event.sendInfoType.name ());
//        	}
        }catch (Exception e) {
            e.printStackTrace ();
        }
    }
}
