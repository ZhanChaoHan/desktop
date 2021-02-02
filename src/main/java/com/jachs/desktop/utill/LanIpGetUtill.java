package com.jachs.desktop.utill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author zhanchaohan
 * 
 */
public class LanIpGetUtill {

    /***
                 * 获取局域网内所有的ip信息 dos命令 arp -a java代码调用dos命令
     */
    public List<String> getInfo () {
        String cmd = "arp -a";
        ArrayList<String> ipInfoList = new ArrayList<String> ();
        try {
            Process process = Runtime.getRuntime ().exec ( cmd );
            InputStream is = process.getInputStream ();
            InputStreamReader isr = new InputStreamReader ( is, "UTF-8" );
            BufferedReader br = new BufferedReader ( isr );

            String readLine = null;
            while ( ( readLine = br.readLine () ) != null ) {
                ipInfoList.add ( readLine );
            }
        }
        catch ( IOException e ) {
            e.printStackTrace ();
        }
        return ipInfoList;
    }

    /***
             * 获取所有IPv4的IP地址：
     */
    public List<String> getLocalIPList () {
        List<String> ipList = new ArrayList<String> ();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces ();
            NetworkInterface networkInterface;
            Enumeration<InetAddress> inetAddresses;
            InetAddress inetAddress;
            String ip;
            while ( networkInterfaces.hasMoreElements () ) {
                networkInterface = networkInterfaces.nextElement ();
                inetAddresses = networkInterface.getInetAddresses ();
                while ( inetAddresses.hasMoreElements () ) {
                    inetAddress = inetAddresses.nextElement ();
                    if ( inetAddress != null && inetAddress instanceof Inet4Address ) { // IPV4
                        ip = inetAddress.getHostAddress ();
                        ipList.add ( ip );
                    }
                }
            }
        }
        catch ( SocketException e ) {
            e.printStackTrace ();
        }
        return ipList;
    }
}
