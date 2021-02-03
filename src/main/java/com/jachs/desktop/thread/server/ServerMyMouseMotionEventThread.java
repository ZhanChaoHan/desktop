package com.jachs.desktop.thread.server;

/**
 * @author zhanchaohan
 * 
 */
public class ServerMyMouseMotionEventThread extends ServerThread implements Runnable {
    private int webSocketPort;
    
    public ServerMyMouseMotionEventThread ( int webSocketPort ) {
        super ();
        this.webSocketPort = webSocketPort;
    }

    public void run () {
        
    }

}
