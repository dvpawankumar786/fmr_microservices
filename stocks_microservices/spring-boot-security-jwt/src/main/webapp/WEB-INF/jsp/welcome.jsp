<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.List" %>
		<%@ page import="java.util.ArrayList" %>
	
	<%@ page import="com.security.jwt.dao.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Dashboard</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
 
    <link rel="stylesheet" type="text/css"
          href="/css/styles.css"/>
</head>
<body>
<div class="hr"></div>

<%
HttpSession sessionsa = request.getSession(false);
String token = (String) sessionsa.getAttribute("token");
String user = (String) sessionsa.getAttribute("user");
response.setHeader("Authorization","Bearer "+token);
List<DropDownList> list = (List<DropDownList>) sessionsa.getAttribute("dlist");

%>
 
<input type="hidden" id="token" name="token" value=<%=token%>>
<div id="formistocks" class="login-wrap">
	<div class="login-html">
	 <div style="text-align:right">
				<input type="submit" id="logoutid" class="button" value="Logout"/>
			</div>
	<div>
	<p> <h2 style="color:#784">Welcome <%=user%> here are the operations <br>
	</p><br>
	</div>
		<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Stocks</label>
		<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Portfolio</label>
		<form class="login-form" id="stockid">
			<div class="sign-in-htm">
<input type="hidden" id="formtype1" name="formtype1" value="1">
				<div class="hr"></div>
		    <div>
    <select name="pid" id="pid">
    <% for(DropDownList d:list)
    {
    	%><option class="group" value=<%=d.getValue()%>><%=d.getName()%></option>
    <% }%>
    </select>
    <br>
    <br>
    <br>
    </div>
	
	<% if(list!=null && list.size()==0)
		{%>
			 <div class="group">
				<p>no data found</p>
			</div>
		<%}%>
	
	<% if(list!=null && list.size()>0)
		{
		if(!list.get(0).getValue().equalsIgnoreCase("fallback"))
		{%>
			 <div class="group">
				<input type="submit" id="stock" class="button" value="Get Stock Details"/>
			</div>
		<%}
		}%> 
		<div><br>
		<br>
		</div>
        
			 <div class="group">
				<input type="submit" id="buystock" class="button" value="Buy Stocks"/>
			</div>
		
			</div>
			<div class="sign-up-htm">
			<input type="hidden" id="formtype2" name="formtype2" value="2">
			
			
				<div class="group">
					<label for="custid" class="label">Enter Customer Id</label>
					<br>
			<br>
					<input id="custid" type="text" class="input">
				</div>
				<br>
				<br>
				
				<% if(list!=null && list.size()>0)
		{
		if(!list.get(0).getValue().equalsIgnoreCase("fallback"))
		{%>
		
			 <div class="group">
					<input type="submit" id="portid" class="button" value="Get Portfolio Details"/>
				</div>
		<%}
		
		else
		{%>
		
		 <div class="group">
				<p>fall back servie from portfolio</p>
			</div>
	<%
			
		}
		}
		%> 
				
				<div class="hr"></div>
				
			</div>
		</form>
	</div>
	
	
</div>
 <div id="feedback"></div>
<script type="text/javascript"
        src="/js/jquery-1.11.1.min.js"></script>
 
<script type="text/javascript" src="/js/stocksjs.js"></script>
 
</body>
</html>