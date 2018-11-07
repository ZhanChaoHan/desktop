package com.jachs.desktop;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;
/****
 * 服務端
 * @author Jachs
 *
 */
public class Desktop extends Frame{
	
	public static void main(String[] args) {
		Dimension screenSize;
		Rectangle screenRectangle;
		ServerSocket serverSocket;
		Socket socket;
		Robot robot;
		try {
			screenSize= Toolkit.getDefaultToolkit().getScreenSize();
			screenRectangle= new Rectangle(screenSize);
			robot=new Robot();
			
			serverSocket=new ServerSocket(22222);
			socket= serverSocket.accept();
			System.out.println("in");
			BufferedImage image;
			ByteArrayOutputStream arrayOutputStream;
			Pictrue pictrue;
			ObjectOutputStream objectOutputStream;
			while(true){
				image= robot.createScreenCapture(screenRectangle);
				arrayOutputStream=new ByteArrayOutputStream();
				ImageIO.write(image, "png", arrayOutputStream);
				
				pictrue=new Pictrue(arrayOutputStream.size(),arrayOutputStream.toByteArray());
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
