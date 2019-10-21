package com.jachs.desktop.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.jachs.desktop.entity.ClickKeyEntity;

/****
 * 键盘点击
 * @author zhanchaohan
 *
 */
public class ClickKey implements Runnable{
	private String serverIp;
	private int clickKeyPort;
	private ClickKeyEntity clickKeyEntity;
	
	public ClickKey(String serverIp, int clickKeyPort, ClickKeyEntity clickKeyEntity) {
		super();
		this.serverIp = serverIp;
		this.clickKeyPort = clickKeyPort;
		this.clickKeyEntity = clickKeyEntity;
	}

	public void run() {
		try {
			Socket socket=new Socket(serverIp, clickKeyPort);
			ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(clickKeyEntity);
			objectOutputStream.flush();
			objectOutputStream.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
