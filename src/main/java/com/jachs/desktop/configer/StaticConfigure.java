package com.jachs.desktop.configer;

import java.io.File;

import com.jachs.desktop.ClientWindow;

/****
 * 
 * @author zhanchaohan
 *
 */
public class StaticConfigure {
	//客戶端窗口圖片素材地址
	 public static final String CLIENTIMAGEPATH = ClientWindow.class.getResource ( "" ).getPath () + File.separator + "image"+ File.separator;
}
