package com.jachs.desktop.server;

import java.io.IOException;
import java.net.ServerSocket;

public class PingPortEvent implements Runnable{
	private Integer pingPort;

	public PingPortEvent(Integer pingPort) {
		super();
		this.pingPort = pingPort;
	}

	public void run() {
		try {
			ServerSocket ss=new ServerSocket(pingPort);
			ss.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
