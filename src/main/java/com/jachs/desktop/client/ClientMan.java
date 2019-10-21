package com.jachs.desktop.client;

import java.util.Properties;


public class ClientMan {
	public static void main(String[] args) throws Exception {
		Properties pr=new Properties();
		pr.load(ClientMan.class.getResourceAsStream("client.properties"));
		
		new ShowPictrue().start(pr.getProperty("serverIP"),Integer.parseInt(pr.getProperty("deskPort")),Integer.parseInt(pr.getProperty("clickPort")),Integer.parseInt(pr.getProperty("clickKeyPort")));
	}
}
