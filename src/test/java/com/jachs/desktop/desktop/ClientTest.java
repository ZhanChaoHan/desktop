package com.jachs.desktop.desktop;


import com.jachs.desktop.ClientWindow;

/**
 * @author zhanchaohan
 */
public class ClientTest {
    public static void main ( String[] args ) throws Exception {
        ClientWindow cw=new ClientWindow();
        
        cw.init ();
        cw.start ();
    }
}
