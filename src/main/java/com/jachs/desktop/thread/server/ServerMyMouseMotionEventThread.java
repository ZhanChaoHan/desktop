package com.jachs.desktop.thread.server;

import java.net.ServerSocket;

import com.jachs.desktop.thread.BaseThread;

/**
 * @author zhanchaohan
 * 
 */
public class ServerMyMouseMotionEventThread extends BaseThread implements Runnable {
    private int webSocketPort;
    
    public ServerMyMouseMotionEventThread ( int webSocketPort ) {
        super ();
        this.webSocketPort = webSocketPort;
    }

    public void run () {
        try {
        	serverSocket = new ServerSocket ( webSocketPort );
			socket = serverSocket.accept ();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
