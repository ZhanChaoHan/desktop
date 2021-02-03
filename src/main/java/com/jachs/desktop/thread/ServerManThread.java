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
    
    public ServerManThread ( ServerPo serverPo ) {
        super ();
        this.serverPo = serverPo;
    }

    public void run () {
        try {
        	ServerSocket  ManServerSocket=new ServerSocket ( serverPo.getPort () );
            
//            Socket manSocket= ManServerSocket.accept ();//单连接
        	Socket manSocket;
        	while((manSocket=ManServerSocket.accept())!=null) {//避免测试一直重启
	            ManEntity manEntity=new ManEntity(serverPo);
	            new ObjectOutputStream (manSocket.getOutputStream ()).writeObject ( manEntity );//传递初始化参数
	            ManServerSocket.close();
        	}
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }

}
