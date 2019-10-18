package com.jachs.desktop.server;

import java.awt.Robot;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.jachs.desktop.entity.Click;

public class ClickEvent implements Runnable{
	private int clickPort;
	
	public ClickEvent(int clickPort) {
		super();
		this.clickPort = clickPort;
	}

	public void run() {
		try {
			ServerSocket ss=new ServerSocket(clickPort);
			Robot robot=new Robot();
			Socket socket;
			while((socket=ss.accept())!=null) {
				ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
				Click click=(Click) ois.readObject();
				switch (click.getClickType()) {
				case 0://鼠标进入
					break;
				case 1://鼠标移出进入
					break;
				case 2://鼠标摁下
					break;
				case 3://鼠标释放
					break;
				case 4://鼠标点击释放位置不变
					break;
				case 5://鼠标移动
					robot.mouseMove(click.getX(),click.getY());
					break;
				case 6://鼠标拖拽
					break;
				}
				System.out.println(click.getX());
			}
			socket.close();
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
