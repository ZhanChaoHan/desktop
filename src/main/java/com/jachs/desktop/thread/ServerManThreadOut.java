package com.jachs.desktop.thread;

import java.io.ObjectOutputStream;

import com.jachs.desktop.entity.ManEntity;

import lombok.extern.slf4j.Slf4j;

/**
 * 初始化线程
 * @author zhanchaohan
 * 
 */
@Slf4j
public class ServerManThreadOut implements Runnable {
    private ObjectOutputStream objectOutputStream;
    private ManEntity manEntity;

    public ServerManThreadOut ( ObjectOutputStream objectOutputStream, ManEntity manEntity ) {
        super ();
        this.objectOutputStream = objectOutputStream;
        this.manEntity = manEntity;
    }

    public ServerManThreadOut ( ManEntity manEntity ) {
        super ();
        this.manEntity = manEntity;
    }

    public void run () {
        try {
            log.info ( "服务端初始化线程开始传送参数" );
            objectOutputStream.writeObject ( manEntity );
            objectOutputStream.flush ();
            log.info ( "服务端初始化线程开始休眠" );
            Thread.sleep ( 10000 );//休眠10秒等待客户端获取连接以及加载配置
        }
        catch ( Exception e ) {
            e.printStackTrace ();
        }
    }

}
