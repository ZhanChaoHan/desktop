package com.jachs.desktop;


import com.jachs.desktop.utill.PicToAviUtil;

/****
 * 客戶端写入视频
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
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
