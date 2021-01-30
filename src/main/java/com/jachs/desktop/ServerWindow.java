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

import javax.imageio.ImageIO;

import com.jachs.desktop.configer.InitProperties;
import com.jachs.desktop.entity.Pictrue;

/****
 * 服務端
 * 
 * @author Jachs
 *
 */
public class ServerWindow extends InitProperties {

    @Override
    public void start () throws IOException {
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
