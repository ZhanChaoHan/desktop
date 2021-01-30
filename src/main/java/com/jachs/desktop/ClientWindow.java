package com.jachs.desktop;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.apache.commons.io.IOUtils;

import com.jachs.desktop.configer.InitProperties;
import com.jachs.desktop.configer.StaticConfigure;
import com.jachs.desktop.thread.WriterPictrueThread;

/****
 * 客戶展示端
 * 
 * @author Jachs
 *
 */
public class ClientWindow extends InitProperties {
    static Frame f = new Frame ();
    public static JLabel imgLabel;
    public static ImageIcon img;
    static boolean exit = false;

    public void start () throws IOException {
        // 关闭窗体K
        f.addWindowListener ( new WindowAdapter () {
            @Override
            public void windowClosing ( WindowEvent e ) {
                exit = true;
                f.setVisible ( false );// 设置窗体的可见性
                Thread WriterAviThread = new Thread ( new WriterAvi ( StaticConfigure.CLIENTIMAGEPATH ) );
                WriterAviThread.start ();
            }
        } );
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

        Thread WriterPictrueThread = new Thread ( new WriterPictrueThread (sp.getIp (),sp.getPort () ) );

        WriterPictrueThread.start ();
    }
}
