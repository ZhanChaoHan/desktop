package com.jachs.desktop.desktop;

import java.util.List;

import org.junit.Test;

import com.jachs.desktop.utill.LanIpGetUtill;

/**
 * @author zhanchaohan
 * 
 */
public class PrintIp {
    LanIpGetUtill lanIpGetUtill=new LanIpGetUtill();
    
    @Test
    public void test() {
        List<String>ipList= lanIpGetUtill.getInfo ();
        
        for ( String ip : ipList ) {
            System.out.println (ip );
        }
        System.out.println ( "--------------------------------" );
        
        List<String> locaipList=lanIpGetUtill.getLocalIPList ();
        
        for ( String string : locaipList ) {
            System.out.println (string  );
        }
    }
}
