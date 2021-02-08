package com.jachs.desktop.entity.po;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 服务端实体对象
 * @author zhanchaohan
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerPo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String ip;
    
    private int PictruePort;
    private int MyKeyBoardEventPort;
    private int MyMouseEventPort;
    private int MyMouseMotionEventPort;
    
}
