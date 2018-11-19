package com.jachs.desktop;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.apache.commons.io.IOUtils;
/****
 * 客戶展示端
 * @author Jachs
 *
 */
public class ShowPictrue {
	
	static Frame f = new Frame();
	static JLabel imgLabel;
	static ImageIcon img;
	public static void main(String[] args) throws UnknownHostException, IOException {
		// 关闭窗体
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.setTitle("抓取桌面");// 添加标题
		f.setSize(800, 800);// 设置窗体的尺寸
		f.setLocation(10, 20);// 设置窗体出现坐标
		f.setLayout(null);// 清除窗体默认布局
		f.setIconImage(Toolkit.getDefaultToolkit().getImage(new File("").getAbsolutePath()+File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"image"+File.separator+"ico.png"));// 设置图标
//		f.setResizable(false);// 禁止窗体改变尺寸
		
		
		InputStream is=ShowPictrue.class.getResourceAsStream("/image/backgroundDefault.png");
		ByteArrayOutputStream arrayOutputStream=new ByteArrayOutputStream();
		IOUtils.copy(is, arrayOutputStream);
		img = new ImageIcon(arrayOutputStream.toByteArray());
		
		imgLabel = new JLabel(img);
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		
		f.add(imgLabel);
		f.setVisible(true);//设置窗体的可见性
		
		new Thread(new WriterPictrue()).start();
	}


}
