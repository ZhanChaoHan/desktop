package com.jachs.desktop.entity;

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
public class ServerPo {
    private String ip;
    private int port;
    
}
