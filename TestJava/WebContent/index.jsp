<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>  
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
        <title>验证码</title>  
        <script language="javascript">  
function myReload() {  
    document.getElementById("CreateCheckCode").src = document  
            .getElementById("CreateCheckCode").src  
            + "?nocache=" + new Date().getTime();  
}  
</script>  
    </head>  
  
    <body>  
        <form action="Check.jsp" method="post">  
            <input name="checkCode" type="text" id="checkCode" title="验证码区分大小写"  
                size="8" ,maxlength="4" />  
            <img src="PictureCheckCode" id="CreateCheckCode" align="middle">  
            <a href="" onclick="myReload()"> 看不清,换一个</a>  
            <input type="submit" value="提交" />  
        </form>  
    </body>  
</html>