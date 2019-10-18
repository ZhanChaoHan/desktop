package com.jachs.desktop.server;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;

import com.jachs.desktop.entity.PictrueEntity;

/****
 * 服務端屏幕
 * @author Jachs
 *
 */
public class Desktop{
	
	public  void start(int deskPort) {
		Dimension screenSize;
		Rectangle screenRectangle;
		ServerSocket serverSocket;
		Socket socket;
		Robot robot;
		try {
			screenSize= Toolkit.getDefaultToolkit().getScreenSize();
			screenRectangle= new Rectangle(screenSize);
			robot=new Robot();
			
			serverSocket=new ServerSocket(deskPort);
			socket= serverSocket.accept();
			BufferedImage image;
			ByteArrayOutputStream arrayOutputStream;
			PictrueEntity pictrue;
			ObjectOutputStream objectOutputStream;
			while(true){
				image= robot.createScreenCapture(screenRectangle);
				arrayOutputStream=new ByteArrayOutputStream();
//				ImageIO.write(image, "jpg", new File("E:\\a\\"+new Date().getTime()+".jpg"));
				ImageIO.write(image, "jpg", arrayOutputStream);
				
				pictrue=new PictrueEntity(arrayOutputStream.size(),arrayOutputStream.toByteArray());
				objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
				objectOutputStream.writeObject(pictrue);
				Thread.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		}
	}
}
