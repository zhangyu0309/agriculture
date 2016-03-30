<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String contextPath = request.getContextPath()+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>

<link href="<%=contextPath%>css/dash/styles.css" rel="stylesheet" type="text/css" />

<!--必要样式-->
<link href="<%=contextPath%>css/dash/goal-thermometer.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>css/dash/normalize.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>css/dash/default.css">
<script type="text/javascript" src="<%=contextPath%>js/jquery-2.0.3.js"></script>
<script type="text/javascript" src="<%=contextPath%>js/dash/goal-thermometer.js"></script>
<script src="<%=contextPath%>js/dash/jquery.speedometer.js"></script>
<script src="<%=contextPath%>js/dash/jquery.jqcanvas-modified.js"></script>
<script src="<%=contextPath%>js/dash/excanvas-modified.js"></script>
<script src="<%=contextPath%>js/dash/raphael-2.1.4.min.js"></script>
<script src="<%=contextPath%>js/dash/justgage.js"></script>
<script type="text/javascript">
var currentAmount = 40;
$(function(){
	determineImageSet();
    $('#test').speedometer();
    $('#test').speedometer({ percentage: 55 || 0 });
	var g1 = new JustGage({
          id: "g1",
          value: 0,
          min: 0,
          max: 5000,
          title: "",
          label: "光照强度(LX)",
          width:"300",
          height:"300",
          float:"-90,0,0,50"
    });
     g1.refresh(getRandomInt(0, 5000));
});
</script>

</head>
<body>

<div id="centered"> 
	<div id="goal-thermometer"></div>
	<div id="test" style="float: left;"> 70 </div>
	<div id="g1" style="float: left;overflow: hidden; position: relative; left: 50px; top: -90px;"></div>
</div>

</body>
</html>