package com.jachs.desktop.thread;

import java.io.ObjectOutputStream;

import com.jachs.desktop.entity.ManEntity;

public class ClientManThreadOut implements Runnable {
	private ObjectOutputStream objectOutputStream;
	private ManEntity manEntity;

	public ClientManThreadOut(ObjectOutputStream objectOutputStream, ManEntity manEntity) {
		super();
		this.objectOutputStream = objectOutputStream;
		this.manEntity = manEntity;
	}

	public ClientManThreadOut(ManEntity manEntity) {
		super();
		this.manEntity = manEntity;
	}


	public void run() {
		try {
			objectOutputStream.writeObject(manEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
