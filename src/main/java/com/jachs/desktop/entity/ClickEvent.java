package com.jachs.desktop.entity;

/**
 * @author zhanchaohan
 * 
 */
public class ClickEvent extends Event{
    private char key;
    
    public ClickEvent ( SendInfoType sendInfoType, char key ) {
        super ( sendInfoType, key );
    }
    public char getKey () {
        return key;
    }


    public void setKey ( char key ) {
        this.key = key;
    }
    
}
