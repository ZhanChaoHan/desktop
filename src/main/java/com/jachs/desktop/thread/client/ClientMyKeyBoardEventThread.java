package com.jachs.desktop.thread.client;

import java.net.ServerSocket;

import com.jachs.desktop.thread.BaseThread;

/**
 * @author zhanchaohan
 * 
 */
public class ClientMyKeyBoardEventThread extends BaseThread implements Runnable {
    private int webSocketPort;
    
    public ClientMyKeyBoardEventThread ( int webSocketPort ) {
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
