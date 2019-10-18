package com.jachs.desktop.client;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.jachs.desktop.entity.ClickEntity;

public class ClickScreen implements Runnable{
	private final String serverIp;
	private final int clickPort;
	private ClickEntity ck;
	
	public ClickScreen(String serverIp, int clickPort, ClickEntity ck) {
		super();
		this.serverIp = serverIp;
		this.clickPort = clickPort;
		this.ck = ck;
	}

	public void run() {
		try {
			Socket socket=new Socket(serverIp, clickPort);
			ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(ck);
			objectOutputStream.flush();
			objectOutputStream.close();
			socket.close();
			System.out.println(ck.getClickType()+"aaaaaaaa");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
