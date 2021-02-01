package com.jachs.desktop.utill;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 创建一个线程池扫描目标机端口
 * @author zhanchaohan
 * 
 */
public class SocketThreadPoolUtill {
    ThreadPoolExecutor  executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(5));
    
}
