package com.jachs.desktop.client;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

import javax.swing.ImageIcon;

import com.jachs.desktop.entity.Pictrue;

/****
 * 客戶端寫入線程
 * 
 * @author Jachs
 *
 */
public class WriterPictrue implements Runnable {
	private String IMAGEPATH;
	private String serverIP;
	private int deskPort;
	public WriterPictrue(String iMAGEPATH, String serverIP, int deskPort) {
		super();
		IMAGEPATH = iMAGEPATH;
		this.serverIP = serverIP;
		this.deskPort = deskPort;
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
			Socket socket = new Socket(serverIP, deskPort);
			while (true && !ShowPictrue.exit) {
				inputStream = new ObjectInputStream(socket.getInputStream());
				pictrue = (Pictrue) inputStream.readObject();

				arrayOutputStream = new ByteArrayOutputStream();
				arrayOutputStream.write(pictrue.getData(), 0, pictrue.getSize());

				outputStream = new FileOutputStream(new File(this.IMAGEPATH + new Date().getTime() + ".jpg"));
				outputStream.write(arrayOutputStream.toByteArray());

				ShowPictrue.img = new ImageIcon(arrayOutputStream.toByteArray());

				ShowPictrue.imgLabel.setIcon(ShowPictrue.img);
				ShowPictrue.imgLabel.setBounds(0, 0, ShowPictrue.img.getIconWidth(), ShowPictrue.img.getIconHeight());
				ShowPictrue.imgLabel.repaint();
				// ShowPictrue.f.repaint();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
