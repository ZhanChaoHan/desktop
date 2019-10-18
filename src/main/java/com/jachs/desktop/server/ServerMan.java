package com.jachs.desktop.server;

import java.util.Properties;

public class ServerMan {

	public static void main(String[] args) throws Exception {
		Properties pr=new Properties();
		pr.load(ServerMan.class.getResourceAsStream("server.properties"));
		
		new Thread(new ClickEvent(Integer.parseInt(pr.getProperty("clickPort")))).start();
		new Desktop().start(Integer.parseInt(pr.getProperty("deskPort")));
	}
}
