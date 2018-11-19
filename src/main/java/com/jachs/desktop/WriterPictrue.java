package com.jachs.desktop;

import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.ImageIcon;

import com.jachs.desktop.entity.Pictrue;
/****
 * 客戶端寫入線程
 * @author Jachs
 *
 */
public class WriterPictrue implements Runnable{

	public void run() {
		ObjectInputStream inputStream;
		Pictrue pictrue;
		ByteArrayOutputStream arrayOutputStream;
		try{
			Socket socket=new Socket("127.0.0.1", 22222);
			while(true){
				inputStream=new ObjectInputStream(socket.getInputStream());
				pictrue= (Pictrue) inputStream.readObject();
				
				arrayOutputStream=new ByteArrayOutputStream();
				arrayOutputStream.write(pictrue.getData(), 0, pictrue.getSize());
				ShowPictrue.img = new ImageIcon(arrayOutputStream.toByteArray());
				
				ShowPictrue.imgLabel.setIcon(ShowPictrue.img);
				ShowPictrue.imgLabel.setBounds(0, 0, ShowPictrue.img.getIconWidth(), ShowPictrue.img.getIconHeight());
				ShowPictrue.imgLabel.repaint();
//				ShowPictrue.f.repaint();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
