<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body onload="checkBrowser()">
	<OBJECT classid="clsid:9BE31822-FDAD-461B-AD51-BE1D1C159921" id="vlc"
		codebase="" width="600" height="480" id="vlc" events="True">
		<param name="MRL" value="" />
		<param name="Src" value="" />
		<param name="ShowDisplay" value="True" />
		<param name="AutoLoop" value="False" />
		<param name="AutoPlay" value="False" />
		<param name="Time" value="True" />
		<EMBED pluginspage="http://www.videolan.org"
			type="application/x-vlc-plugin" version="VideoLAN.VLCPlugin.2"
			text="Waiting for video" name="vlc" target="file:///C:/Users/Administrator/Desktop/oow2010-2.flv"></EMBED>
	</OBJECT>
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
            if(!isInsalledFFVLC()){  
            	alert("请先安装VLC插件");
            	location.href="http://download.videolan.org/pub/videolan/vlc/2.2.1/";
            } 
        }else if(browser=="Microsoft Internet Explorer" && version>=4) {  
            if(!isInsalledIEVLC()){
            	alert("请先安装VLC插件");
            	location.href="http://download.videolan.org/pub/videolan/vlc/2.2.1/";
            }  
        }  
    }  
</script>
</html>
