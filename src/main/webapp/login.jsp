<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<meta name="description" content="">
<meta name="author" content="ThemeBucket">
<title>登录</title>
<!-- bootstrap样式 -->
<link href="css/bootstrap/style.css" rel="stylesheet">
<link href="css/bootstrap/style-responsive.css" rel="stylesheet">
<!-- bootstrap JS  -->
<script src="js/jquery-2.0.3.js"></script>
<script src="js/bootstrap/bootstrap.min.js"></script>
<script src="js/bootstrap/modernizr.min.js"></script>
<!-- IE9 以下浏览器的兼容JS -->
<script src="js/bootstrap/html5shiv.js"></script>
<script src="js/bootstrap/respond.min.js"></script>
<script type="text/javascript">
   
   $(function(){
       getRandomImge = function(){
           $("#randomNum").attr("src","jcaptcha.jpg"); 
       };
   });

</script>
</head>
<body class="login-body">
	<div class="container">
		<form class="form-signin" method="post" action="loginAdmin.do">
			<div class="form-signin-heading text-center">
				<h1 class="sign-title">后台管理系统</h1>
				<!--<img src="img/login/login-logo.png" alt="" />-->
			</div>
			<div class="login-wrap">
				<input type="text" name="userId" class="form-control" value="${userId }" placeholder="账号"autofocus> 
				<input type="password" name="passwd" class="form-control"placeholder="密码">
				<!--<input type="text" name="randomCode" value="" class="form-control" placeholder="验证码" style="width: 70%;float: left;">
				<img id="randomNum" src="<%=path %>/jcaptcha.jpg" style="width: 30%;height: 35px;" onclick="getRandomImge();">-->
				<button class="btn btn-lg btn-login btn-block" type="submit">登 录</button>
				<div class="registration">
					<font color="red">${loginMsg}</font>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
