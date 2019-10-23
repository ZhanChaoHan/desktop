package com.jachs.desktop.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;


public class ClientMan {
	public static void main(String[] args) throws InterruptedException, IOException {
		String command = "arp -a";
		Runtime r = Runtime.getRuntime();
		Process p;
		p = r.exec(command);
		Thread.sleep(1000);
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
		Set<String> ip = new HashSet<String>();
		while (br.ready()) {
			String ipLine = br.readLine();
			if (ipLine.indexOf(".") != -1) {
				int start = ipLine.indexOf(".") - 3;
				int end = ipLine.lastIndexOf(".") + 4;
				ip.add(ipLine.substring(start, end).trim());
			}
		}
		Properties pr = new Properties();
		pr.load(ClientMan.class.getResourceAsStream("client.properties"));
		for (String ips : ip) {
			Socket socket=new Socket(); 
			try {
				socket.connect(new InetSocketAddress(ips,Integer.parseInt(pr.getProperty("pingPort"))),100);
				if (socket.isConnected()) {
					socket.close();
					new ShowPictrue().start(ips,Integer.parseInt(pr.getProperty("deskPort")),Integer.parseInt(pr.getProperty("clickPort")),Integer.parseInt(pr.getProperty("clickKeyPort")));
					break;
				}
			} catch (Exception e) {
				socket.close();
				continue;
			}
		}
	}
}
