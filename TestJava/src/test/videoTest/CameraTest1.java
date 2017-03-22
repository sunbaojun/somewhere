package test.videoTest;

import java.awt.*;
import java.util.*;
import javax.media.Format;
import javax.media.Manager;
import javax.media.Player;
import javax.media.format.VideoFormat;
import javax.swing.*;
import java.awt.event.*;
/**
 * 第二种方法是先获取所有的采集设备列表，然后从中挑选出满足条件的摄像头设备
 */
public class CameraTest1 extends JFrame{
    /**多媒体采集对象列表*/
    Vector<javax.media.CaptureDeviceInfo>  deviceList=javax.media.CaptureDeviceManager.getDeviceList(null);
    javax.media.MediaLocator mediaLocator=null;
    Player player=null;
    /** Creates a new instance of CameraTest1 */
    public CameraTest1() {
        this.setTitle("测试本地摄像头");
        this.initCamera();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void initCamera(){
        //查找所需要的多媒体采集程序,以vfw开头即可
        javax.media.format.VideoFormat currentFormat = null;
        for(int i=0;i<deviceList.size();i++){
            System.out.println("获取的设备名为:"+this.deviceList.get(i).getName());
            if(deviceList.get(i).getName().startsWith("vfw")){
                Format [] vedioFormat=deviceList.get(i).getFormats();
                for(int j=0;j<vedioFormat.length;i++){
                    if(vedioFormat[i] instanceof javax.media.format.VideoFormat){
                        currentFormat=(VideoFormat) vedioFormat[i];
                        break;
                    }
                }
                //判断如果得到当前的视频格式为空，则显示错误信息，否则获取其定位器
                if(currentFormat==null){
                    System.out.println("发生错误!");
                }
                this.mediaLocator=deviceList.get(i).getLocator();
                System.out.println("获取的采集媒体定位器为:"+this.mediaLocator);
                this.createPlayerByMediaLocator();
                break;
            }
        }
    }

    public void createPlayerByMediaLocator(){
        try {
            System.out.println(this.mediaLocator);
            player   =   Manager.createRealizedPlayer(this.mediaLocator);
            player.start();
            Component   comp;
            if   ((comp   =   player.getVisualComponent())   !=   null) {
                this.getContentPane().add(comp, "Center");
            }
        } catch   (Exception   e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CameraTest1();
    }
}
