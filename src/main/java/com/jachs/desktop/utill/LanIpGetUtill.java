package com.jachs.desktop.utill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取局域网内所有的ip信息 dos命令 arp -a java代码调用dos命令
 * 
 * @author zhanchaohan
 * 
 */
public class LanIpGetUtill {

    public List<String> getInfo () {
        String cmd = "arp -a";
        ArrayList<String> ipInfoList = new ArrayList<String> ();
        try {
            Process process = Runtime.getRuntime ().exec ( cmd );
            InputStream is = process.getInputStream ();
            InputStreamReader isr = new InputStreamReader ( is,"UTF-8" );
            BufferedReader br = new BufferedReader ( isr );

            String readLine=null;
            while ((readLine= br.readLine ())!=null ) {
                ipInfoList.add ( readLine );
            }
        }
        catch ( IOException e ) {
            e.printStackTrace ();
        }
        return ipInfoList;
    }
}
