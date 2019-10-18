package com.jachs.desktop.client;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
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

import com.jachs.desktop.WriterAvi;
import com.jachs.desktop.entity.Click;

/****
 * 客戶展示端
 * 
 * @author Jachs
 *
 */
public class ShowPictrue {

	static Frame f = new Frame();
	static JLabel imgLabel;
	static ImageIcon img;
	static final String IMAGEPATH = ShowPictrue.class.getResource("").getPath() + File.separator + "image"
			+ File.separator;
	static boolean exit=false;
	public  void start(final String serverIp,int deskPort,final int clickPort) throws UnknownHostException, IOException, InterruptedException {
		// 关闭窗体K
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit=true;
				f.setVisible(false);// 设置窗体的可见性
				Thread WriterAviThread = new Thread(new WriterAvi(IMAGEPATH));
				WriterAviThread.start();
			}
		});
		f.setTitle("抓取桌面");// 添加标题
		f.setSize(800, 800);// 设置窗体的尺寸
		f.setLocation(10, 20);// 设置窗体出现坐标
		f.setLayout(null);// 清除窗体默认布局
		f.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(new File("").getAbsolutePath() + File.separator + "src" + File.separator + "main"
						+ File.separator + "java" + File.separator + "image" + File.separator + "ico.png"));// 设置图标
		// f.setResizable(false);// 禁止窗体改变尺寸

		InputStream is = ShowPictrue.class.getResourceAsStream("/image/backgroundDefault.png");
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		IOUtils.copy(is, arrayOutputStream);
		img = new ImageIcon(arrayOutputStream.toByteArray());

		imgLabel = new JLabel(img);
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());

		f.add(imgLabel);
		f.setVisible(true);// 设置窗体的可见性
		
		f.addMouseListener(new MouseListener() {
			Click ck=new Click();
			
			public void mouseEntered(MouseEvent e) {
//				ck.setClickType(0);
//				ck.setX(e.getX());
//				ck.setY(e.getY());
//				System.out.println("鼠标进入"+e.getX()+"\t\t"+e.getY());
			}
			public void mouseExited(MouseEvent e) {
//				ck.setClickType(1);
//				ck.setX(e.getX());
//				ck.setY(e.getY());
//				System.out.println("鼠标移出"+e.getX()+"\t\t"+e.getY());
			}
			
			public void mousePressed(MouseEvent e) {
				ck.setX(e.getX());
				ck.setY(e.getY());
				System.out.println("鼠标摁下"+e.getX()+"\t\t"+e.getY());
				if(e.getClickCount()%2==0) {//鼠标点击次数
					ck.setClickOnce(false);
				}else {//单击
					ck.setClickOnce(true);
				}
				if(e.getModifiers()==4) {//鼠标反键点击
					ck.setClickType(7);
				}else {
					ck.setClickType(2);
				}
//				new Thread(new ClickScreen(serverIp,clickPort,ck)).start();
			}
			public void mouseReleased(MouseEvent e) {
				ck.setClickType(3);
				ck.setX(e.getX());
				ck.setY(e.getY());
				System.out.println("鼠标释放"+e.getX()+"\t\t"+e.getY());
//				new Thread(new ClickScreen(serverIp,clickPort,ck)).start();
			}
			public void mouseClicked(MouseEvent e) {
				ck.setClickType(4);
				ck.setX(e.getX());
				ck.setY(e.getY());
				System.out.println("鼠标点击释放位置不变触发"+"\t\t"+e.getY());
//				new Thread(new ClickScreen(serverIp,clickPort,ck)).start();
			}
		});
		f.addMouseMotionListener(new MouseMotionListener() {
			Click ck=new Click();
			public void mouseMoved(MouseEvent e) {
				ck.setClickType(5);
				ck.setX(e.getX());
				ck.setY(e.getY());
//				System.out.println("鼠标移动"+"\t\t"+e.getY());
//				new Thread(new ClickScreen(serverIp,clickPort,ck)).start();
			}
			
			public void mouseDragged(MouseEvent e) {
				ck.setClickType(6);
				ck.setX(e.getX());
				ck.setY(e.getY());
				System.out.println("鼠标摁住拖拽"+"\t\t"+e.getY());
//				new Thread(new ClickScreen(serverIp,clickPort,ck)).start();
			}
		});
		new Thread(new WriterPictrue(IMAGEPATH,serverIp,deskPort)).start();;
	}
}
