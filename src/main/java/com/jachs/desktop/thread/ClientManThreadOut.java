package com.jachs.desktop.thread;

import java.io.ObjectOutputStream;

import com.jachs.desktop.entity.ManEntity;

public class ClientManThreadOut implements Runnable {
	private ObjectOutputStream objectOutputStream;

	public ClientManThreadOut(ObjectOutputStream objectOutputStream) {
		super();
		this.objectOutputStream = objectOutputStream;
	}



	public void run() {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
