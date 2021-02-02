package com.jachs.desktop;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;

import com.jachs.desktop.configer.InitPropertiesInterFace;
import com.jachs.desktop.entity.Pictrue;
import com.jachs.desktop.entity.po.ServerPo;
import com.jachs.desktop.utill.LanIpGetUtill;

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
    
    public void init () throws IOException {
        pro.load ( ServerWindow.class.getResourceAsStream ( "/server.properties" ) );
        
        List<String>ipList= lanIpGetUtill.getLocalIPList ();
        
        sp.setIp ( ipList.get ( 1) );//字段绑定本机Ip
        sp.setPort ( Integer.parseInt (pro.getProperty ( "server.start.port" )));
    }

    public void start () {
        Dimension screenSize;
        Rectangle screenRectangle;
        ServerSocket serverSocket;
        Socket socket;
        Robot robot;
        try {
            screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
            screenRectangle = new Rectangle ( screenSize );
            robot = new Robot ();

            serverSocket = new ServerSocket ( sp.getPort () );
            socket = serverSocket.accept ();
            BufferedImage image;
            ByteArrayOutputStream arrayOutputStream;
            Pictrue pictrue;
            ObjectOutputStream objectOutputStream;
            while ( true ) {
                image = robot.createScreenCapture ( screenRectangle );
                arrayOutputStream = new ByteArrayOutputStream ();
                ImageIO.write ( image, "jpg", arrayOutputStream );

                pictrue = new Pictrue ( arrayOutputStream.size (), arrayOutputStream.toByteArray () );
                objectOutputStream = new ObjectOutputStream ( socket.getOutputStream () );
                objectOutputStream.writeObject ( pictrue );
                Thread.sleep ( 100 );
            }
        }
        catch ( Exception e ) {
            e.printStackTrace ();
        }
        finally {
        }
    }

}
