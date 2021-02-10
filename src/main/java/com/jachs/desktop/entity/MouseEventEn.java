package com.jachs.desktop.entity;

/**
 * @author zhanchaohan
 * 
 */
public class MouseEventEn extends Event{
    private int id;//500鼠标点击,501鼠标摁下,502鼠标松开最后触发
    private int button;//1鼠标点击,3鼠标反击
    
    public MouseEventEn ( SendInfoType sendInfoType ) {
        super ( sendInfoType );
    }
    public int getId () {
        return id;
    }
    public void setId ( int id ) {
        this.id = id;
    }
    public int getButton () {
        return button;
    }
    public void setButton ( int button ) {
        this.button = button;
    }

}
