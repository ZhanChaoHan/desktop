package com.jachs.desktop.thread;

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
public class BaseThread implements InitPropertiesInterFace{
	protected Dimension screenSize;
    protected Rectangle screenRectangle;
    protected ServerSocket serverSocket;
    protected Socket socket;
    protected Robot robot;
    
    public BaseThread () {
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
