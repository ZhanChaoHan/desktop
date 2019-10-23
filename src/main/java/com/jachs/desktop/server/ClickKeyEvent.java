package com.jachs.desktop.server;

import java.awt.Robot;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.TreeSet;

import com.jachs.desktop.entity.ClickKeyEntity;

/*****
 * 键盘点击
 * @author zhanchaohan
 *
 */
public class ClickKeyEvent implements Runnable{
	private int clickKeyPort;
	
	public ClickKeyEvent(int clickKeyPort) {
		super();
		this.clickKeyPort = clickKeyPort;
	}

	public void run() {
		try {
			ServerSocket ss=new ServerSocket(clickKeyPort);
			Socket socket;
			Robot r=new Robot();
			while((socket=ss.accept())!=null) {
				ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
				ClickKeyEntity clickKey=(ClickKeyEntity) ois.readObject();
				TreeSet<Integer> ts= clickKey.getClickKey();
				if(ts.size()==1) {//单键
					for (Integer integer : ts) {
						r.keyPress(integer);
						r.keyRelease(integer);
					}
				}else {//多键点击
					for (Integer integer : ts) {
						r.keyPress(integer);
					}
					for (Integer integer : ts) {
						r.keyRelease(integer);
					}
				}
			}
			socket.close();
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
