package com.jachs.desktop.thread;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import com.jachs.desktop.configer.StaticConfigure;
import com.jachs.desktop.entity.ManEntity;

/**
 * 初始化线程
 * @author zhanchaohan
 * 
 */
public class ClientManThread implements Runnable{
    private String ServerHost;
    private int ServerPort;
    
    
    public ClientManThread(String serverHost, int serverPort) {
		super();
		ServerHost = serverHost;
		ServerPort = serverPort;
	}


	public void run () {
    	try {
    	  InputStream inputStream=new Socket ( ServerHost, ServerPort ).getInputStream ();
          ObjectInputStream objectInputStream=new ObjectInputStream(inputStream);
          StaticConfigure.MANENTITY= (ManEntity) objectInputStream.readObject ();
          objectInputStream.close ();
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    }

}
