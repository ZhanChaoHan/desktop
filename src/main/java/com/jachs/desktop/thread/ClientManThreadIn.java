package com.jachs.desktop.thread;

import java.io.ObjectInputStream;

import com.jachs.desktop.configer.StaticConfigure;
import com.jachs.desktop.entity.ManEntity;

/****
 *@author zhanchaohan
 */
public class ClientManThreadIn implements Runnable {
	private ObjectInputStream objectInputStream;

	public ClientManThreadIn(ObjectInputStream objectInputStream) {
		super();
		this.objectInputStream = objectInputStream;
	}


	public void run() {
		try {
			while ((StaticConfigure.MANENTITY = (ManEntity) objectInputStream.readObject()) != null) {
				objectInputStream.close();
				StaticConfigure.inintSuccess=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
