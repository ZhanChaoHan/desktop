package com.jachs.desktop;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.apache.commons.io.IOUtils;

import com.jachs.desktop.configer.InitPropertiesInterFace;
import com.jachs.desktop.configer.StaticConfigure;
import com.jachs.desktop.entity.po.ClientPo;
import com.jachs.desktop.event.ClientKeyBoardEvent;
import com.jachs.desktop.event.ClientMouseEvent;
import com.jachs.desktop.event.ClientMouseMotionEvent;
import com.jachs.desktop.thread.client.ClientWriterAviThread;
import com.jachs.desktop.thread.client.ClientWriterPictrueThread;

import lombok.extern.slf4j.Slf4j;

/****
 * 客戶展示端
 * 
 * @author zhanchaohan
 *
 */
@Slf4j
public class ClientWindow implements InitPropertiesInterFace {
    private Properties pro = new Properties ();
    private ClientPo cp = new ClientPo ();

    static Frame f = new Frame ();
    public static JLabel imgLabel;
    public static ImageIcon img;

    public void init () throws Exception {
        log.info ( "客户端加载配置文件" );
        pro.load ( ServerWindow.class.getResourceAsStream ( "/client.properties" ) );

        cp.setServerHost ( pro.getProperty ( "server.start.ip" ) );
        
        cp.setPictruePort(Integer.parseInt ( pro.getProperty ( "server.start.pictrue.port" ) ) );
        cp.setMyKeyBoardEventPort(Integer.parseInt ( pro.getProperty ( "server.start.clientkeyboard.port" ) ) );
        cp.setMyMouseEventPort(Integer.parseInt ( pro.getProperty ( "server.start.clientmouse.port" ) ) );
        cp.setMyMouseMotionEventPort(Integer.parseInt ( pro.getProperty ( "server.start.clientmousemotion.port" ) ) );
        
        cp.setHigh ( Integer.parseInt ( pro.getProperty ( "client.window.high" ) ) );
        cp.setWidth ( Integer.parseInt ( pro.getProperty ( "client.window.width" ) ) );
        cp.setX ( Integer.parseInt ( pro.getProperty ( "clent.init.position.x" ) ) );
        cp.setY ( Integer.parseInt ( pro.getProperty ( "clent.init.position.y" ) ) );
        
        StaticConfigure.WRITERVIDERO=Boolean.parseBoolean ( pro.getProperty ( "client.writer.video" ) );
    }

    public void start () throws Exception {
    	Socket PictrueSk=new Socket(cp.getServerHost(), cp.getPictruePort());
    	Socket MyKeyBoardSk=new Socket(cp.getServerHost(), cp.getMyKeyBoardEventPort());
    	Socket MyMouseSk=new Socket(cp.getServerHost(), cp.getMyMouseEventPort());
    	Socket ServerMyMouseMotionSk=new Socket(cp.getServerHost(), cp.getMyMouseMotionEventPort());
    	
//        if ( !StaticConfigure.inintSuccess ) {
//            log.info ( "客户端初始化参数失败程序退出" );
//            JOptionPane.showMessageDialog ( f, "初始化参数失败请检查配置文件", "标题", JOptionPane.WARNING_MESSAGE );
//            System.exit ( 0 );
//        }
        // 关闭窗体K
        f.addWindowListener ( new WindowAdapter () {
            @Override
            public void windowClosing ( WindowEvent e ) {// 窗体关闭监听事件
                f.setVisible ( false );// 设置窗体的可见性
                Thread WriterAviThread = new Thread ( new ClientWriterAviThread () );
                WriterAviThread.start ();// 将图片写入为视屏文件
                try {
                    WriterAviThread.join ();
                    System.exit ( 0 );
                }
                catch ( InterruptedException e1 ) {
                    e1.printStackTrace ();
                }

            }
        } );
        f.addMouseListener ( new ClientMouseEvent () );// 添加鼠标事件监听
        f.addKeyListener ( new ClientKeyBoardEvent () );// 添加键盘事件监听
        f.addMouseMotionListener ( new ClientMouseMotionEvent () );// 添加鼠标移动拖动事件监听
        log.info ( "客户端事件监听以加载" );
        
        f.setTitle ( "抓取桌面" );// 添加标题
        f.setSize ( cp.getHigh (), cp.getHigh () );// 设置窗体的尺寸
        f.setLocation ( cp.getX (), cp.getY () );// 设置窗体出现坐标
        f.setLayout ( null );// 清除窗体默认布局
        f.setIconImage ( Toolkit.getDefaultToolkit ()
                .getImage ( new File ( "" ).getAbsolutePath () + File.separator + "src" + File.separator + "main"
                        + File.separator + "java" + File.separator + "image" + File.separator + "ico.png" ) );// 设置图标
        // f.setResizable(false);// 禁止窗体改变尺寸

        InputStream is = ClientWindow.class.getResourceAsStream ( "/image/backgroundDefault.png" );
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream ();
        IOUtils.copy ( is, arrayOutputStream );
        img = new ImageIcon ( arrayOutputStream.toByteArray () );

        imgLabel = new JLabel ( img );
        imgLabel.setBounds ( 0, 0, img.getIconWidth (), img.getIconHeight () );

        f.add ( imgLabel );
        f.setVisible ( true );// 设置窗体的可见性
        
        
        new Thread(new ClientWriterPictrueThread(new ObjectInputStream(PictrueSk.getInputStream()))).start();
        StaticConfigure.ClientKeyBoardEventOos=new ObjectOutputStream(MyKeyBoardSk.getOutputStream());
        StaticConfigure.ClientMouseEventOos=new ObjectOutputStream(MyMouseSk.getOutputStream());
        StaticConfigure.ClientMouseMotionEventOos=new ObjectOutputStream(ServerMyMouseMotionSk.getOutputStream());
        
    }
}
