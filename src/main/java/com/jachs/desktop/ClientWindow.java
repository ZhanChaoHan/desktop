package com.jachs.desktop;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.apache.commons.io.IOUtils;

import com.jachs.desktop.configer.InitPropertiesInterFace;
import com.jachs.desktop.entity.po.ClientPo;
import com.jachs.desktop.event.MyKeyBoardEvent;
import com.jachs.desktop.event.MyMouseEvent;
import com.jachs.desktop.event.MyMouseMotionEvent;
import com.jachs.desktop.thread.WriterAviThread;
import com.jachs.desktop.thread.WriterPictrueThread;

/****
 * 客戶展示端
 * 
 * @author Jachs
 *
 */
public class ClientWindow implements InitPropertiesInterFace {
    private Properties pro=new Properties ();
    private ClientPo cp=new ClientPo();
    
    static Frame f = new Frame ();
    public static JLabel imgLabel;
    public static ImageIcon img;
    static boolean exit = false;

    public void init () throws IOException {
        pro.load ( ServerWindow.class.getResourceAsStream ( "/client.properties" ) );
        
        cp.setServerHost ( pro.getProperty ( "server.start.ip" ));
        cp.setPort ( Integer.parseInt ( pro.getProperty ( "server.start.port" ) ) );
        
        cp.setHigh ( Integer.parseInt (pro.getProperty ( "client.window.high" )) );
        cp.setWidth ( Integer.parseInt (pro.getProperty ( "client.window.width" )) );
        cp.setX ( Integer.parseInt (pro.getProperty ( "clent.init.position.x" )) );
        cp.setY ( Integer.parseInt (pro.getProperty ( "clent.init.position.y" )) );
    }

    public void start () throws IOException {
        // 关闭窗体K
        f.addWindowListener ( new WindowAdapter () {
            @Override
            public void windowClosing ( WindowEvent e ) {//窗体关闭监听事件
                exit = true;
                f.setVisible ( false );// 设置窗体的可见性
                Thread WriterAviThread = new Thread ( new WriterAviThread () );
                WriterAviThread.start ();//将图片写入为视屏文件
            }
        } );
        f.addMouseListener ( new MyMouseEvent () );//添加鼠标事件监听
        f.addKeyListener ( new MyKeyBoardEvent () );//添加键盘事件监听
        f.addMouseMotionListener ( new MyMouseMotionEvent () );//添加鼠标移动拖动事件监听

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

        Thread WriterPictrueThread = new Thread ( new WriterPictrueThread ( cp.getServerHost (), cp.getPort ()) );

        WriterPictrueThread.start ();
    }

}
