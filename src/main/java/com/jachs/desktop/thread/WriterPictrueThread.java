package com.jachs.desktop.thread;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

import javax.swing.ImageIcon;

import com.jachs.desktop.ClientWindow;
import com.jachs.desktop.entity.Pictrue;

/****
 * 客戶端寫入線程
 * 
 * @author Jachs
 *
 */
public class WriterPictrueThread implements Runnable {
	private String IMAGEPATH;

	public WriterPictrueThread(String iMAGEPATH) {
		super();
		IMAGEPATH = iMAGEPATH;
	}

	public void run() {
		ObjectInputStream inputStream;
		Pictrue pictrue;
		ByteArrayOutputStream arrayOutputStream;
		OutputStream outputStream;
		try {
			File files = new File(this.IMAGEPATH);
			if (!files.exists()) {
				files.mkdirs();
			}
			Socket socket = new Socket("127.0.0.1", 22222);
			while (true && !ClientWindow.exit) {
				inputStream = new ObjectInputStream(socket.getInputStream());
				pictrue = (Pictrue) inputStream.readObject();

				arrayOutputStream = new ByteArrayOutputStream();
				arrayOutputStream.write(pictrue.getData(), 0, pictrue.getSize());

				outputStream = new FileOutputStream(new File(this.IMAGEPATH + new Date().getTime() + ".jpg"));
				outputStream.write(arrayOutputStream.toByteArray());

				ClientWindow.img = new ImageIcon(arrayOutputStream.toByteArray());

				ClientWindow.imgLabel.setIcon(ClientWindow.img);
				ClientWindow.imgLabel.setBounds(0, 0, ClientWindow.img.getIconWidth(), ClientWindow.img.getIconHeight());
				ClientWindow.imgLabel.repaint();
				// ClientWindow.f.repaint();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
