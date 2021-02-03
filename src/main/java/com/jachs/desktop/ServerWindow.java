package com.jachs.desktop;

import java.io.IOException;
import java.util.List;
import java.util.Properties;


import com.jachs.desktop.configer.InitPropertiesInterFace;
import com.jachs.desktop.entity.po.ServerPo;
import com.jachs.desktop.thread.ServerManThread;
import com.jachs.desktop.thread.server.ServerMyKeyBoardEventThread;
import com.jachs.desktop.thread.server.ServerMyMouseEventThread;
import com.jachs.desktop.thread.server.ServerMyMouseMotionEventThread;
import com.jachs.desktop.thread.server.ServerPictrueThread;
import com.jachs.desktop.utill.LanIpGetUtill;
import com.jachs.desktop.utill.SocketCheckOutUtill;

/****
 * 服務端
 * 
 * @author zhanchaohan
 *
 */
public class ServerWindow implements InitPropertiesInterFace {
    private Properties pro=new Properties ();
    private LanIpGetUtill lanIpGetUtill=new LanIpGetUtill();
    private ServerPo sp=new ServerPo();
    
    SocketCheckOutUtill socketCheckOutUtill=new SocketCheckOutUtill();
    
    public void init () throws IOException {
        pro.load ( ServerWindow.class.getResourceAsStream ( "/server.properties"));
        
        List<String>ipList= lanIpGetUtill.getLocalIPList ();
        
        sp.setIp ( ipList.get ( 0) );//字段绑定本机Ip
        sp.setPort ( Integer.parseInt (pro.getProperty ( "server.start.port")));
        
        sp.setPictruePort(( socketCheckOutUtill.CheckRangPort (
                ipList.get ( 0),
                Integer.parseInt (pro.getProperty ( "server.port.start")),
                Integer.parseInt (pro.getProperty ( "server.port.end" )))));
        
        new Thread (new ServerPictrueThread ( sp.getPictruePort () )).start ();
        
        sp.setMyKeyBoardEventPort ( socketCheckOutUtill.CheckRangPort (
                ipList.get ( 0),
                Integer.parseInt (pro.getProperty ( "server.port.start")),
                Integer.parseInt (pro.getProperty ( "server.port.end" ))));
        new Thread (new ServerMyKeyBoardEventThread ( sp.getMyKeyBoardEventPort () )).start ();
        
        sp.setMyMouseEventPort ( socketCheckOutUtill.CheckRangPort (
                ipList.get ( 0),
                Integer.parseInt (pro.getProperty ( "server.port.start")),
                Integer.parseInt (pro.getProperty ( "server.port.end" ))));
        new Thread (new ServerMyMouseEventThread ( sp.getMyMouseEventPort () )).start ();
        
        sp.setMyMouseMotionEventPort ( socketCheckOutUtill.CheckRangPort (
                ipList.get ( 0),
                Integer.parseInt (pro.getProperty ( "server.port.start" )),
                Integer.parseInt (pro.getProperty ( "server.port.end" ))) );
        new Thread (new ServerMyMouseMotionEventThread ( sp.getMyMouseMotionEventPort () )).start ();
        
        
        new Thread(new ServerManThread(sp)).start ();
    }

    public void start () {
        
        
        
    }

}
