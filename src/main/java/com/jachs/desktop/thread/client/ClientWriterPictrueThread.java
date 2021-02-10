package com.jachs.desktop.thread.client;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.swing.ImageIcon;

import com.jachs.desktop.ClientWindow;
import com.jachs.desktop.configer.StaticConfigure;
import com.jachs.desktop.entity.Pictrue;

/****
 * 客戶端图片寫入線程
 * 
 * @author Jachs
 *
 */
public class ClientWriterPictrueThread implements Runnable {
	private ObjectInputStream inputStream;

    public ClientWriterPictrueThread(ObjectInputStream inputStream) {
		super();
		this.inputStream = inputStream;
	}

	public void run() {
		Pictrue pictrue;
		ByteArrayOutputStream arrayOutputStream;
		OutputStream outputStream;
		try {
			File files = new File(StaticConfigure.CLIENTIMAGEPATH);
			if (!files.exists()) {
				files.mkdirs();
			}
			while (true) {
				pictrue = (Pictrue) inputStream.readObject();
				
				arrayOutputStream = new ByteArrayOutputStream();
				arrayOutputStream.write(pictrue.getData(), 0, pictrue.getSize());
				if(StaticConfigure.WRITERVIDERO) {
    				outputStream = new FileOutputStream(new File(StaticConfigure.CLIENTIMAGEPATH+File.separator+ new Date().getTime() + ".jpg"));
    				outputStream.write(arrayOutputStream.toByteArray());
    				outputStream.close();
				}
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
