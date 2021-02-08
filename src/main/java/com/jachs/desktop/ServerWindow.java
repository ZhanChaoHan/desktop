package com.jachs.desktop;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Properties;

import com.jachs.desktop.configer.InitPropertiesInterFace;
import com.jachs.desktop.configer.StaticConfigure;
import com.jachs.desktop.entity.ManEntity;
import com.jachs.desktop.entity.po.ServerPo;
import com.jachs.desktop.thread.server.ServerMyKeyBoardEventThread;
import com.jachs.desktop.thread.server.ServerMyMouseEventThread;
import com.jachs.desktop.thread.server.ServerMyMouseMotionEventThread;
import com.jachs.desktop.thread.server.ServerPictrueThread;
import com.jachs.desktop.utill.LanIpGetUtill;
import com.jachs.desktop.utill.SocketCheckOutUtill;

import lombok.extern.slf4j.Slf4j;

/****
 * 服務端
 * 
 * @author zhanchaohan
 *
 */
@Slf4j
public class ServerWindow implements InitPropertiesInterFace {
	private Properties pro = new Properties();
	private LanIpGetUtill lanIpGetUtill = new LanIpGetUtill();
	private ServerPo sp = new ServerPo();

	SocketCheckOutUtill socketCheckOutUtill = new SocketCheckOutUtill();

	public void init() throws Exception {
		log.info("服务端加载配置文件");
		pro.load(ServerWindow.class.getResourceAsStream("/server.properties"));

		List<String> ipList = lanIpGetUtill.getLocalIPList();

		sp.setIp(ipList.get(0));// 字段绑定本机Ip
		
		sp.setPictruePort(Integer.parseInt ( pro.getProperty ( "server.start.pictrue.port" ) ) );
		sp.setMyKeyBoardEventPort(Integer.parseInt ( pro.getProperty ( "server.start.clientkeyboard.port" ) ) );
		sp.setMyMouseEventPort(Integer.parseInt ( pro.getProperty ( "server.start.clientmouse.port" ) ) );
		sp.setMyMouseMotionEventPort(Integer.parseInt ( pro.getProperty ( "server.start.clientmousemotion.port" ) ) );
		log.info("服务端加载配置文件完毕");
	}

	public void start() throws Exception {
		log.info("服务端端口准备开启");
		ServerSocket PictrueSs=new ServerSocket(sp.getPictruePort());
		ServerSocket MyKeyBoardSs=new ServerSocket(sp.getMyKeyBoardEventPort());
		ServerSocket MyMouseSs=new ServerSocket(sp.getMyMouseEventPort());
		ServerSocket ServerMyMouseMotionSs=new ServerSocket(sp.getMyMouseMotionEventPort());
		
		Socket PictrueSk=PictrueSs.accept();
		log.info("服务端的图片端口连接成功");
		Socket MyKeyBoardSk=MyKeyBoardSs.accept();
		log.info("服务端的键盘监听端口连接成功");
		Socket MyMouseSk=MyMouseSs.accept();
		log.info("服务端的鼠标移动端口连接成功");
		Socket ServerMyMouseMotionSk=ServerMyMouseMotionSs.accept();
		log.info("服务端的鼠标拖动端口连接成功");
		
		new Thread(new ServerPictrueThread(new ObjectOutputStream(PictrueSk.getOutputStream()))).start();
		new Thread(new ServerMyKeyBoardEventThread(new ObjectInputStream(MyKeyBoardSk.getInputStream()))).start();
		new Thread(new ServerMyMouseEventThread(new ObjectInputStream(MyMouseSk.getInputStream()))).start();
		new Thread(new ServerMyMouseMotionEventThread(new ObjectInputStream(ServerMyMouseMotionSk.getInputStream())));
		
	}

}
