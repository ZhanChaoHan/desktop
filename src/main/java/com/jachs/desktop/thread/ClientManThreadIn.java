package com.jachs.desktop.thread;

import java.io.ObjectInputStream;

import com.jachs.desktop.configer.StaticConfigure;
import com.jachs.desktop.entity.ManEntity;

import lombok.extern.slf4j.Slf4j;

/****
 * @author zhanchaohan
 */
@Slf4j
public class ClientManThreadIn implements Runnable {
    private ObjectInputStream objectInputStream;

    public ClientManThreadIn ( ObjectInputStream objectInputStream ) {
        super ();
        this.objectInputStream = objectInputStream;
    }

    public void run () {
        try {
            while ( ( StaticConfigure.MANENTITY = (ManEntity) objectInputStream.readObject () ) != null ) {
                objectInputStream.close ();
                StaticConfigure.inintSuccess = true;
                log.info ( "客户端初始化线程初始化完毕" );
            }
        }
        catch ( Exception e ) {
            e.printStackTrace ();
        }

    }

}
