package com.jachs.desktop.thread.server;

import com.jachs.desktop.thread.BaseThread;

/**
 * @author zhanchaohan
 * 
 */
public class ServerMyMouseEventThread extends BaseThread implements Runnable {
    private int webSocketPort;
    
    public ServerMyMouseEventThread ( int webSocketPort ) {
        super ();
        this.webSocketPort = webSocketPort;
    }


    public void run () {
        
    }

}
