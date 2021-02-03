package com.jachs.desktop.thread.server;

import java.net.ServerSocket;

/**
 * @author zhanchaohan
 * 
 */
public class ServerMyKeyBoardEventThread extends ServerThread implements Runnable {
    private int webSocketPort;
    
    public ServerMyKeyBoardEventThread ( int webSocketPort ) {
        super ();
        this.webSocketPort = webSocketPort;
    }

    public void run () {
        try {
            serverSocket = new ServerSocket ( webSocketPort );
            socket = serverSocket.accept ();
            
            
        }catch (Exception e) {
            e.printStackTrace ();
        }
    }

}
