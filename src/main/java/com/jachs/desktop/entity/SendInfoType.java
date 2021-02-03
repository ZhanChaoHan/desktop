package com.jachs.desktop.entity;

/**
 * @author zhanchaohan
 * 
 */
public enum SendInfoType {
    InitThread(0);
    
    private int infoType;

    private SendInfoType ( int infoType ) {
        this.infoType = infoType;
    }
    
}
