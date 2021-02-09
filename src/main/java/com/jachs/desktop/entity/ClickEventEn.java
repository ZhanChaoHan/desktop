package com.jachs.desktop.entity;

/**
 * @author zhanchaohan
 * 
 */
public class ClickEventEn extends Event{
    private char key;
    
    public ClickEventEn ( SendInfoType sendInfoType, char key ) {
        super ( sendInfoType );
        this.key = key;
    }


    public char getKey () {
        return key;
    }


    public void setKey ( char key ) {
        this.key = key;
    }
    
}
