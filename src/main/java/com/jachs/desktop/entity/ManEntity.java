package com.jachs.desktop.entity;

import java.io.Serializable;

import com.jachs.desktop.entity.po.ServerPo;

/**
 * 初始化序列化对象
 * @author zhanchaohan
 * 
 */
public class ManEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    private SendInfoType sendInfoType=SendInfoType.InitThread;
    
    private ServerPo serverPo;

    public ManEntity () {
        super ();
    }
    public ManEntity ( ServerPo serverPo ) {
        super ();
        this.serverPo = serverPo;
    }

    public SendInfoType getSendInfoType () {
        return sendInfoType;
    }

    public void setSendInfoType ( SendInfoType sendInfoType ) {
        this.sendInfoType = sendInfoType;
    }

    public ServerPo getServerPo () {
        return serverPo;
    }

    public void setServerPo ( ServerPo serverPo ) {
        this.serverPo = serverPo;
    }
    
}
