package com.jachs.desktop.configer;

import java.io.File;
import java.net.ServerSocket;


/****
 * 
 * @author zhanchaohan
 *
 */
public class StaticConfigure {
	//客戶端窗口圖片素材地址
	 public static final String CLIENTIMAGEPATH = new File("").getAbsolutePath() + File.separator + "image"+ File.separator;
	 
	 public static ServerSocket ManServerSocket;
	 public static ServerSocket MyKeyBoardEventServerSocket;
	 public static ServerSocket MyMouseEventThreadServerSocket;
	 public static ServerSocket MyMouseMotionEventThreadServerSocket;
}
