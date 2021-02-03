package com.jachs.desktop.thread;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.jachs.desktop.configer.StaticConfigure;
import com.jachs.desktop.entity.ManEntity;
import com.jachs.desktop.entity.po.ServerPo;

/**
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
            StaticConfigure.ManServerSocket=new ServerSocket ( serverPo.getPort () );
            
            Socket manSocket= StaticConfigure.ManServerSocket.accept ();//单连接
            ManEntity manEntity=new ManEntity(serverPo);
            new ObjectOutputStream (manSocket.getOutputStream ()).writeObject ( manEntity );//传递初始化参数
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }

}
