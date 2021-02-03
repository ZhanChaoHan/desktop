package com.jachs.desktop.thread.client;

import com.jachs.desktop.thread.BaseThread;

/**
 * @author zhanchaohan
 * 
 */
public class ClientMyMouseMotionEventThread extends BaseThread implements Runnable {
    private int webSocketPort;
    
    public ClientMyMouseMotionEventThread ( int webSocketPort ) {
        super ();
        this.webSocketPort = webSocketPort;
    }

    public void run () {
        
    }

}
