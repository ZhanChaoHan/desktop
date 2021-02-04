package com.jachs.desktop.thread;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.jachs.desktop.entity.ManEntity;
import com.jachs.desktop.entity.po.ServerPo;

/**
 * 初始化线程
 * @author zhanchaohan
 * 
 */
public class ServerManThreadOut implements Runnable{
	private ObjectOutputStream objectOutputStream;
	private ManEntity manEntity;

	public ServerManThreadOut(ObjectOutputStream objectOutputStream, ManEntity manEntity) {
		super();
		this.objectOutputStream = objectOutputStream;
		this.manEntity = manEntity;
	}

	public ServerManThreadOut(ManEntity manEntity) {
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
