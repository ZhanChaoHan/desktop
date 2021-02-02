package com.jachs.desktop.utill;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 扫描区间端口
 * @author zhanchaohan
 * 
 */
public class SocketCheckOutUtill {
    public int CheckRangPort ( String hostIp , int star , int end ) throws UnknownHostException {
        for ( int k = star ; k < end ; k++ ) {
            if(OccupiedOrNot(hostIp,k)) {
                return k;
            };
        }
        return 0;
    }

    public boolean OccupiedOrNot ( String hostIp , int port ) throws UnknownHostException {
        boolean flag = false;
        InetAddress theAddress = InetAddress.getByName ( hostIp );
        try {
            Socket socket = new Socket ( theAddress, port );
            flag = true;
        }
        catch ( IOException e ) {

        }
        return flag;
    }
}
