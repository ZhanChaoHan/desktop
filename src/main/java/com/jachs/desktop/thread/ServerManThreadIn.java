package com.jachs.desktop.thread;

import java.io.ObjectInputStream;

import com.jachs.desktop.configer.StaticConfigure;
import com.jachs.desktop.entity.ManEntity;

/**
 * 初始化线程
 * @author zhanchaohan
 * 
 */
/*
public class ServerManThreadIn implements Runnable {
    private ObjectInputStream objectInputStream;

    public ServerManThreadIn ( ObjectInputStream objectInputStream ) {
        super ();
        this.objectInputStream = objectInputStream;
    }

    public void run () {
        try {
            while ( ( StaticConfigure.MANENTITY = (ManEntity) objectInputStream.readObject () ) != null ) {
                objectInputStream.close ();
            }
        }
        catch ( Exception e ) {
            e.printStackTrace ();
        }
    }
}
*/