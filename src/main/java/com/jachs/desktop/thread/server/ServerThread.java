package com.jachs.desktop.thread.server;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


import com.jachs.desktop.configer.InitPropertiesInterFace;

/**
 * @author zhanchaohan
 * 
 */
public class ServerThread implements InitPropertiesInterFace{
    Dimension screenSize;
    Rectangle screenRectangle;
    ServerSocket serverSocket;
    Socket socket;
    Robot robot;
    
    public ServerThread () {
        init ();
    }

    public void init ()  {
        try {
            screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
            screenRectangle = new Rectangle ( screenSize );
            robot = new Robot ();
        }catch (Exception e) {
            e.printStackTrace ();
        }
    }
    
    public void start () throws IOException {
        
    }
}
