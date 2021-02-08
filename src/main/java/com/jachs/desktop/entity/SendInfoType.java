package com.jachs.desktop.entity;

/**
 * @author zhanchaohan
 * 
 */
public enum SendInfoType {
    InitThread(0),//初始化
	KeyBoard(1),//键盘点击
	ClientMouse(2),//鼠标移动
	ClientMouseMotion(3);//鼠标移动
	
    private int infoType;

    private SendInfoType ( int infoType ) {
        this.infoType = infoType;
    }
    
}
