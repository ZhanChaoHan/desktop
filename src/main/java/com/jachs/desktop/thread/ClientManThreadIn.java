package com.jachs.desktop.thread;

import java.io.ObjectInputStream;

import com.jachs.desktop.configer.StaticConfigure;
import com.jachs.desktop.entity.ManEntity;

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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
