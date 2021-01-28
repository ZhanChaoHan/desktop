package com.jachs.desktop.desktop;

import java.io.IOException;

import com.jachs.desktop.ClientWindow;

/**
 * @author zhanchaohan
 */
public class ClientTest {
    public static void main ( String[] args ) throws IOException {
        ClientWindow cw=new ClientWindow();
        
        cw.init ();
        cw.start ();
    }
}
