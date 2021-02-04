package com.jachs.desktop.thread.server;

import java.io.ObjectInputStream;
import java.net.ServerSocket;

import com.jachs.desktop.entity.KeyBoardEvent;
import com.jachs.desktop.thread.BaseThread;

/**
 * @author zhanchaohan
 * 
 */
public class ServerMyKeyBoardEventThread extends BaseThread implements Runnable {
    private int webSocketPort;
    ObjectInputStream ois;
    public ServerMyKeyBoardEventThread ( int webSocketPort ) {
        super ();
        this.webSocketPort = webSocketPort;
    }

    public void run () {
        try {
        	serverSocket = new ServerSocket ( webSocketPort );
            socket = serverSocket.accept ();
            ois=new ObjectInputStream(socket.getInputStream());
            KeyBoardEvent keyBoardEvent;
            while((keyBoardEvent=(KeyBoardEvent) ois.readObject())!=null) {
            	System.out.println(keyBoardEvent.getKey());
            }
        }catch (Exception e) {
            e.printStackTrace ();
        }
    }

}
