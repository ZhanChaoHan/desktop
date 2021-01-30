package com.jachs.desktop.configer;

import java.io.IOException;
import java.util.Properties;

import com.jachs.desktop.entity.ClientPo;
import com.jachs.desktop.entity.ServerPo;

/**
 * @author zhanchaohan
 * 
 */
public abstract class InitProperties implements InitPropertiesInterFace{
    private Properties pro=new Properties ();
    public ServerPo sp=new ServerPo();
    public ClientPo cp=new ClientPo();
    
    //初始化配置文件参数
    public void init () {
        try {
            pro.load ( InitProperties.class.getResourceAsStream ( "/application.properties" ) );
            
            sp.setPort ( Integer.parseInt (pro.getProperty ( "server.init.port" )) );
            
            cp.setPort(Integer.parseInt (pro.getProperty ( "client.init.port" )));
            cp.setHigh ( Integer.parseInt (pro.getProperty ( "client.window.high" )) );
            cp.setWidth ( Integer.parseInt (pro.getProperty ( "client.window.width" )) );
            cp.setX ( Integer.parseInt (pro.getProperty ( "clent.init.position.x" )) );
            cp.setY ( Integer.parseInt (pro.getProperty ( "clent.init.position.y" )) );
            
        }catch ( IOException e ) {
            e.printStackTrace();
        }
    }
    //启动
    public abstract void start() throws IOException;
}
