package com.jachs.desktop.entity;

import java.io.Serializable;

/***
 * 
 * @author zhanchaohan
 *
 */
public abstract class Event implements Serializable{
    private static final long serialVersionUID = 1L;
    private SendInfoType sendInfoType;
    
    
    public Event ( SendInfoType sendInfoType) {
        super ();
        this.sendInfoType = sendInfoType;
    }


    public SendInfoType getSendInfoType () {
        return sendInfoType;
    }


    public void setSendInfoType ( SendInfoType sendInfoType ) {
        this.sendInfoType = sendInfoType;
    }
 
	
}
