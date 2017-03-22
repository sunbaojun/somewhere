package test.videoTest;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.media.CaptureDeviceInfo;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.cdm.CaptureDeviceManager;
import javax.swing.JFrame;
public class CameraTest extends JFrame{
    public   static   Player   player   =   null;
    private   CaptureDeviceInfo   di   =   null;
    private   MediaLocator   ml   =   null;
    /** Creates a new instance of CameraTest */
    public CameraTest() {
        init();
        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    public void init(){
        String   str1   =   "vfw:Logitech   USB   Video   Camera:0";      //获取USB摄像头的字符串
        String   str2   =   "vfw:Microsoft WDM Image Capture (Win32):0";    //获取本地摄像头的字符串
        di   =   CaptureDeviceManager.getDevice(str2);     //根据字符串获取采集设备（摄像头）的引用
        System.out.println(di);         //显示采集设备(摄像头)的信息
        System.out.println(di.getName());     //显示采集设备（摄像头）的设备名称
        ml   =   di.getLocator();      //获取采集设备的定位器的引用，需要根据此引用来创建视频播放器
        try {
            player   =   Manager.createRealizedPlayer(ml);
            player.start();
            Component   comp;
            if   ((comp   =   player.getVisualComponent())   !=   null) {
                //如果摄像头视频组件不如空，加到窗体中
                this.getContentPane().add(comp,   BorderLayout.NORTH);
            }
        } catch   (Exception   e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new CameraTest();
    }
}
