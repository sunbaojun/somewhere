<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>  
    <%-- <%  
        String checkcode=request.getParameter("checkCode"); 
    	System.out.println(checkcode);
        if(checkcode==null || checkcode.equals("")){  
            out.print("<script>alert('请输入验证码');window.location.href('index.jsp')</script>");  
        }else{  
            if(!checkcode.equalsIgnoreCase((String)session.getAttribute("randCheckCode"))){  
                out.print("<script>alert('验证码不正确,请重新输入');history.back(-1);</script>");  
            }else{  
                out.print("登录成功");  
            }  
        }  
     %> --%>  
     <div>
     	<object type="application/x-shockwave-flash" data="vcastr3.swf" width="650" height="500" id="vcastr3"> 
			<param name="movie" value="vcastr3.swf"/> 
			<param name="allowFullScreen" value="true" /> 
			<param name="FlashVars" value="xml=  
			<vcastr> 
				<channel> 
					<item> 
						<source>http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv</source> 
						<duration></duration> 
						<title></title> 
					</item> 
				</channel> 
				<config> 
					<bufferTime>4</bufferTime> 
					<contralPanelAlpha>0.75</contralPanelAlpha> 
					<controlPanelBgColor>0xff6600</controlPanelBgColor> 
					<controlPanelBtnColor>0xffffff</controlPanelBtnColor> 
					<contralPanelBtnGlowColro>0xffff00</contralPanelBtnGlowColro> 
					<controlPanelMode>float</controlPanelMode> 
					<defautVolume>0.8</defautVolume> 
					<isAutoPlay>true</isAutoPlay> 
					<isLoadBegin>true</isLoadBegin> 
					<isShowAbout>false</isShowAbout>
					<scaleMode>showAll</scaleMode> 
				</config> 
			</vcastr>"/> 
		</object>
     </div>
  </body>  
</html>