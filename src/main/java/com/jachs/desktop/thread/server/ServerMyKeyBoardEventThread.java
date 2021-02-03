package com.jachs.desktop.thread.server;

import java.net.ServerSocket;

import com.jachs.desktop.thread.BaseThread;

/**
 * @author zhanchaohan
 * 
 */
public class ServerMyKeyBoardEventThread extends BaseThread implements Runnable {
    private int webSocketPort;
    
    public ServerMyKeyBoardEventThread ( int webSocketPort ) {
        super ();
        this.webSocketPort = webSocketPort;
    }

    public void run () {
        try {
            
            
        }catch (Exception e) {
            e.printStackTrace ();
        }
    }

}
