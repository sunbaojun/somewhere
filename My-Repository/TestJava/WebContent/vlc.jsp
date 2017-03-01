<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>web camera test</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

</head>

<body bgcolor="white" text="black" onload="checkBrowser()">
<embed type="application/x-vlc-plugin" pluginspage="http://www.videola.org"
    width="640" height="480" id="vlc" version="VideoLAN.VLCPlugin.2" autoplay="yes" loop="no" target="rtsp://user:passwd@192.168.1.34:554" >

</body>
<script type="text/javascript">  
         //仅适用于IE浏览器是，并且安装有vlc插件，则返回true；  
            function isInsalledIEVLC(){    
                var vlcObj = null;  
                var vlcInstalled= false;   
                try {  
                    vlcObj = new ActiveXObject("VideoLAN.Vlcplugin.1");   
                    if( vlcObj != null ){   
                        vlcInstalled = true   
                    }  
                } catch (e) {  
                    vlcInstalled= false;  
                }          
                return vlcInstalled;  
            }   

            //仅适用于firefox浏览器是，并且安装有vlc插件，则返回true；  
            function isInsalledFFVLC(){  
                 var numPlugins=navigator.plugins.length;  
                 for  (i=0;i<numPlugins;i++){   
                      plugin=navigator.plugins[i];  
                      if(plugin.name.indexOf("VideoLAN") > -1 || plugin.name.indexOf("VLC") > -1){   
                         return true;  
                    }  
                 }  
                 return false;  
            }  

                   /* 浏览器检测 */  
            function checkBrowser(){  
                var browser=navigator.appName  
                var b_version=navigator.appVersion  
                var version=parseFloat(b_version)  
                if ( browser=="Netscape"  && version>=4) {  
                    if(isInsalledFFVLC()){  
                        alert("已装VLC插件");  
                    }else{  
                        alert("未装VLC插件");
            location.href="http://download.videolan.org/pub/videolan/vlc/2.2.1/";  
                    }  
                }else if(browser=="Microsoft Internet Explorer" && version>=4) {  
                    if(isInsalledIEVLC()){  
                        alert("已装VLC插件");  
                    }else{  
                        alert("未装VLC插件,请先安装插件");
                    location.href="http://download.videolan.org/pub/videolan/vlc/2.2.1/";
                    }  
                }  
            }  
     </script>
</html>