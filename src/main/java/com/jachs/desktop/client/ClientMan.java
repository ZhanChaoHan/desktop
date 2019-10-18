package com.jachs.desktop.client;

import java.util.Properties;

import com.jachs.desktop.server.ServerMan;

public class ClientMan {
	public static void main(String[] args) throws Exception {
		Properties pr=new Properties();
		pr.load(ServerMan.class.getResourceAsStream("server.properties"));
		
		new ShowPictrue().start(pr.getProperty("serverIP"),Integer.parseInt(pr.getProperty("deskPort")),Integer.parseInt(pr.getProperty("clickPort")));
	}
}
