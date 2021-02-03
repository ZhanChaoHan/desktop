package com.jachs.desktop.thread.client;

import com.jachs.desktop.thread.BaseThread;

/**
 * @author zhanchaohan
 * 
 */
public class ClientMyMouseEventThread extends BaseThread implements Runnable {
    private int webSocketPort;
    
    public ClientMyMouseEventThread ( int webSocketPort ) {
        super ();
        this.webSocketPort = webSocketPort;
    }


    public void run () {
        
    }

}
