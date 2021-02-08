package com.jachs.desktop.thread.server;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;

import com.jachs.desktop.entity.Pictrue;
import com.jachs.desktop.thread.BaseThread;

/**
 * @author zhanchaohan
 * 
 */
public class ServerPictrueThread extends BaseThread implements Runnable {
	private ObjectOutputStream objectOutputStream;


    public ServerPictrueThread(ObjectOutputStream objectOutputStream) {
		super();
		this.objectOutputStream = objectOutputStream;
	}


	public void run () {
        try {
            BufferedImage image;
            ByteArrayOutputStream arrayOutputStream;
            Pictrue pictrue;
            while ( true ) {
                image = robot.createScreenCapture ( screenRectangle );
                arrayOutputStream = new ByteArrayOutputStream ();
                ImageIO.write ( image, "jpg", arrayOutputStream );

                pictrue = new Pictrue ( arrayOutputStream.size (), arrayOutputStream.toByteArray () );
                objectOutputStream.writeObject ( pictrue );
                Thread.sleep ( 100 );
            }
        }
        catch ( Exception e ) {
            e.printStackTrace ();
        }
    }

}
