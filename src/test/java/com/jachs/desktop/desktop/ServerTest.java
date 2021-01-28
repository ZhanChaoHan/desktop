package com.jachs.desktop.desktop;

import java.io.IOException;

import com.jachs.desktop.ServerWindow;

/**
 * @author zhanchaohan
 */
public class ServerTest {
    
    public static void main ( String[] args ) throws IOException {
        ServerWindow sw=new ServerWindow();
        
        sw.init ();
        sw.start ();
    }

}
