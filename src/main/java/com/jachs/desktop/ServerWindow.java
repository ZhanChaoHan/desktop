package com.jachs.desktop;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Properties;


import com.jachs.desktop.configer.InitPropertiesInterFace;
import com.jachs.desktop.configer.StaticConfigure;
import com.jachs.desktop.entity.ManEntity;
import com.jachs.desktop.entity.SendInfoType;
import com.jachs.desktop.entity.po.ServerPo;
import com.jachs.desktop.thread.server.ServerMyKeyBoardEventThread;
import com.jachs.desktop.thread.server.ServerMyMouseEventThread;
import com.jachs.desktop.thread.server.ServerMyMouseMotionEventThread;
import com.jachs.desktop.thread.server.ServerPictrueThread;
import com.jachs.desktop.utill.LanIpGetUtill;
import com.jachs.desktop.utill.SocketCheckOutUtill;

import lombok.extern.slf4j.Slf4j;

/****
 * 服務端
 * 
 * @author zhanchaohan
 *
 */
@Slf4j
public class ServerWindow implements InitPropertiesInterFace {
    private Properties pro=new Properties ();
    private LanIpGetUtill lanIpGetUtill=new LanIpGetUtill();
    private ServerPo sp=new ServerPo();
    
    SocketCheckOutUtill socketCheckOutUtill=new SocketCheckOutUtill();
    
    public void init () throws Exception{
        log.info ( "服务端加载配置文件" );
        pro.load ( ServerWindow.class.getResourceAsStream ( "/server.properties"));
        
        List<String>ipList= lanIpGetUtill.getLocalIPList ();
        
        sp.setIp ( ipList.get ( 0) );//字段绑定本机Ip
        sp.setPort ( Integer.parseInt (pro.getProperty ( "server.start.port")));
        int startPort=Integer.parseInt (pro.getProperty ( "server.port.start"));//端口起始
        int endPort=Integer.parseInt (pro.getProperty ( "server.port.end"));//端口终止
        
        for (int mk = startPort; mk <endPort; mk++) {
        	StaticConfigure.PORTLIST.add(mk);
		}
        sp.setPictruePort(socketCheckOutUtill.CheckRangPort (ipList.get ( 0)));
        sp.setMyKeyBoardEventPort (socketCheckOutUtill.CheckRangPort (ipList.get ( 0)));
        sp.setMyMouseEventPort (socketCheckOutUtill.CheckRangPort (ipList.get ( 0)));
        sp.setMyMouseMotionEventPort (socketCheckOutUtill.CheckRangPort (ipList.get ( 0)));
        
        log.info ( "服务端开启初始化线程" );
        ServerSocket serverSocket=new ServerSocket ( sp.getPort () );
        Socket socket;
        while((socket=serverSocket.accept ())!=null) {
            ManEntity manEntity=new ManEntity();
            manEntity.setSendInfoType ( SendInfoType.InitThread );
            manEntity.setServerPo ( sp );
            
            ObjectOutputStream objectOutputStream=new ObjectOutputStream ( socket.getOutputStream () );
            objectOutputStream.writeObject ( manEntity );
            objectOutputStream.flush ();
            
            ObjectInputStream objectInputStream=new ObjectInputStream ( socket.getInputStream ());
            
            while(objectInputStream.readObject ()!=null) {
                
            }
            
//            new Thread(new ServerManThreadIn(new ObjectInputStream (socket.getInputStream ()))).start();
//            new Thread (new ServerManThreadOut(new ObjectOutputStream ( socket.getOutputStream () ),manEntity)).start ();;
//            Thread.sleep ( 10000 );
        }
    }

    public void start () {
        log.info ( "服务端开启" );
    	new Thread (new ServerPictrueThread ( sp.getPictruePort () )).start ();
    	new Thread (new ServerMyKeyBoardEventThread ( sp.getMyKeyBoardEventPort () )).start ();
    	new Thread (new ServerMyMouseEventThread ( sp.getMyMouseEventPort () )).start ();
    	new Thread (new ServerMyMouseMotionEventThread ( sp.getMyMouseMotionEventPort () )).start ();
    }

}
