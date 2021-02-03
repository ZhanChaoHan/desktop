package com.jachs.desktop.thread.server;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

import javax.imageio.ImageIO;

import com.jachs.desktop.entity.Pictrue;

/**
 * @author zhanchaohan
 * 
 */
public class ServerPictrueThread extends ServerThread implements Runnable {
    private int webSocketPort;

    public ServerPictrueThread ( int webSocketPort ) {
        super ();
        this.webSocketPort = webSocketPort;
    }

    public void run () {
        try {
            serverSocket = new ServerSocket ( webSocketPort );
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
    }

}
