package com.jachs.desktop;

import java.io.IOException;

/***
 * 
 * @author zhanchaohan
 *
 */
public class Main {
	public static void main(String[] args) throws Exception {
		String windowsType = args[0];
		if (windowsType.equalsIgnoreCase("server")) {
			ServerWindow sw = new ServerWindow();

			sw.init();
			System.out.println("服务器启动成功");
			sw.start();
		}
		if (windowsType.equalsIgnoreCase("client")) {
			ClientWindow cw = new ClientWindow();

			cw.init();
			System.out.println("客户端启动成功");
			cw.start();
		}
	}
}
