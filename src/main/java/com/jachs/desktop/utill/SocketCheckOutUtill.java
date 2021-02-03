package com.jachs.desktop.utill;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.jachs.desktop.configer.StaticConfigure;


/**
 * 扫描区间端口
 * @author zhanchaohan
 * 
 */
public class SocketCheckOutUtill {
    public int CheckRangPort ( String hostIp) throws UnknownHostException {
    	for (Integer k : StaticConfigure.PORTLIST) {
    		 if(!OccupiedOrNot(hostIp,k)) {
    			 StaticConfigure.PORTLIST.remove(k);
                 return k;
             };
		}
        return 0;
    }

    public boolean OccupiedOrNot ( String hostIp , int port ) throws UnknownHostException {
        boolean flag = false;
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(hostIp, port), 5000); // 建立连接
            flag=socket.isConnected ();
        }catch (Exception e) {

        }finally {
        }
        return flag;
    }
}
