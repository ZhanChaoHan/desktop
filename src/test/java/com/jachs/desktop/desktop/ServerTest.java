package com.jachs.desktop.desktop;


import com.jachs.desktop.ServerWindow;

/**
 * @author zhanchaohan
 */
public class ServerTest {
    
    public static void main ( String[] args ) throws Exception {
        ServerWindow sw=new ServerWindow();
        
        sw.init ();
        sw.start ();
    }

}
