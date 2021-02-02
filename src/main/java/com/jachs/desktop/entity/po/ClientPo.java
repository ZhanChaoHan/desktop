package com.jachs.desktop.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 客户端实体对象
 * @author zhanchaohan
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientPo {
    private String serverHost;
	private int port;
    private int high;
    private int width;
    
    private int x;
    private int y;
}
