<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.List" %>
		<%@ page import="java.util.ArrayList" %>
	
	<%@ page import="com.security.jwt.dao.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Done</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
 
    <link rel="stylesheet" type="text/css"
          href="/css/styles.css"/>
</head>
<body>

<div class="hr"></div>
 
<div id="formistocks" class="login-wrap">

	<div class="login-html">
	
	<div style="text-align:center" class="group">
				<input type="submit" id="home1" class="button" value="home"/>
			</div>
	<div><h2 style="color:#784">Stock has been bought!</h2></div>
		

	</div>
	
	<div class="hr"></div>
	
</div>
 <div id="feedback"></div>
<script type="text/javascript"
        src="/js/jquery-1.11.1.min.js"></script>
 
<script type="text/javascript" src="/js/home.js"></script>
 
</body>
</html>