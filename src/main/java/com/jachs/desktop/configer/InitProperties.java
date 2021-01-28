package com.jachs.desktop.configer;

import java.io.IOException;
import java.util.Properties;

/**
 * @author zhanchaohan
 * 
 */
public abstract class InitProperties implements InitPropertiesInterFace{
    Properties pro;
    
    public void init () {
        try {
            pro.load ( InitProperties.class.getResourceAsStream ( "application.properties" ) );
            
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
    }

}
