package com.jachs.desktop.configer;

import java.io.File;
import java.util.LinkedList;

import com.jachs.desktop.entity.ManEntity;

/****
 * 
 * @author zhanchaohan
 *
 */
public class StaticConfigure {
	//客戶端窗口圖片素材地址
	 public static final String CLIENTIMAGEPATH = new File("").getAbsolutePath() + File.separator + "image"+ File.separator;
	 
	 public static ManEntity MANENTITY;
	 public static LinkedList<Integer>PORTLIST=new LinkedList<Integer>();
}
