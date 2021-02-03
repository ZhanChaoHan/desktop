package com.jachs.desktop.thread.server;

/**
 * @author zhanchaohan
 * 
 */
public class ServerMyMouseEventThread extends ServerThread implements Runnable {
    private int webSocketPort;
    
    public ServerMyMouseEventThread ( int webSocketPort ) {
        super ();
        this.webSocketPort = webSocketPort;
    }


    public void run () {
        
    }

}
