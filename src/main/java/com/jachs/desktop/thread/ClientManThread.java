package com.jachs.desktop.thread;

import java.io.ObjectInputStream;
import java.net.Socket;

import com.jachs.desktop.configer.StaticConfigure;
import com.jachs.desktop.entity.ManEntity;

public class ClientManThread implements Runnable {
	private Socket socket;

	public ClientManThread(Socket socket) {
		super();
		this.socket = socket;
	}

	public void run() {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			while ((StaticConfigure.MANENTITY = (ManEntity) objectInputStream.readObject()) != null) {
				objectInputStream.close();
			}
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
