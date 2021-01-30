package com.jachs.desktop.thread;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.jachs.desktop.configer.StaticConfigure;
import com.jachs.desktop.utill.PicToAviUtil;

/****
 * 客戶端写入视频线程
 * 
 * @author Jachs
 *
 */
public class WriterAvi implements Runnable {
	private String IMAGEPATH;

	public WriterAvi(String iMAGEPATH) {
		super();
		IMAGEPATH = iMAGEPATH;
	}

	public void run() {
		try {
			PicToAviUtil.convertPicToAvi(IMAGEPATH, "demo.avi", 3, 1440, 860);
			try {
				FileUtils.deleteDirectory(new File(StaticConfigure.CLIENTIMAGEPATH));//删除图片素材
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
