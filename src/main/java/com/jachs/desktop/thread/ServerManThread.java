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
public class ServerManThread implements Runnable{
    private ServerPo serverPo;
    private ServerSocket  ManServerSocket;
    

    public ServerManThread(ServerPo serverPo, ServerSocket manServerSocket) {
		super();
		this.serverPo = serverPo;
		ManServerSocket = manServerSocket;
	}


	public void run () {
        try {
            ObjectOutputStream oos;;
            ManEntity manEntity=new ManEntity();
            manEntity.setServerPo(serverPo);
//            Socket manSocket= ManServerSocket.accept ();//单连接
        	Socket manSocket;
        	while((manSocket=ManServerSocket.accept())!=null) {//避免测试一直重启
        		oos=new ObjectOutputStream(manSocket.getOutputStream());
        		oos.writeObject(manEntity);
        	}
        	
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }

}
