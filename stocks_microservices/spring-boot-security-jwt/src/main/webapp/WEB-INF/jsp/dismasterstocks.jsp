<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.List" %>
		<%@ page import="java.util.ArrayList" %>
		<%@ page import="com.security.jwt.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Buy Stocks</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
 
    <link rel="stylesheet" type="text/css"
          href="/css/styles.css"/>
</head>
<body>
<%
HttpSession sessionsa = request.getSession(false);
String token = (String) sessionsa.getAttribute("token");
response.setHeader("Authorization","Bearer "+token);
List<Stocks> stockslist = (List<Stocks>) sessionsa.getAttribute("masterstockslist");
%>
<div class="hr"></div>
 
<div id="formmasterstocks" class="login-wrap">

	<div class="login-html">
	<input type="hidden" id="token" name="token" value=<%=token%>>
	<div style="text-align:right" class="group">
				<input type="submit" id="home1" class="button" value="home"/>
			</div>
	<div><h2 style="color:#784">Buy Stocks</h2></div>
		<div>
				<table  border="1" cellpadding="0" cellspacing="0">
				<tr>
				<td>symbol</td>
				<td>type</td>
				<td>lastDividend</td>
				<td>fixedDividend</td>
				<td>parValue</td>
				<td>tickerPrice</td>
				
				</tr>
				
    <%
       if(null!=stockslist && stockslist.size()>0 && stockslist.get(0).getTransFlag()!=null && stockslist.get(0).getTransFlag().equalsIgnoreCase("S") )
       {
    	   
        for(Stocks s:stockslist)
        {
            %>
                <tr>
                 
                     <td id="symbol"><%= s.getSymbol()%></td><td id="type"><%= s.getType()%></td><td  id="lastDividend"><%= s.getLastDividend()%></td><td  id="fixedDividend"><%= s.getFixedDividend()%></td><td  id="parValue"><%= s.getParValue()%></td><td  id="tickerPrice"><%= s.getTickerPrice()%></td><td><input type="radio" id="buystock" name="select" value="select" required></td>
                </tr>
               
            <% 
        }
       }
       else if(null!=stockslist && stockslist.size()>0 && stockslist.get(0).getTransFlag()!=null && stockslist.get(0).getTransFlag().equalsIgnoreCase("F") )
       {%>
    	   <div><p><%=stockslist.get(0).getTransStatus()%></p></div>
       <%}
    
    %>
</table>  
			</div>
			<div>
			<br></div>
<div class="group">
					<input type="submit" id="buystockid" class="button" value="Buy This Stock" />
				</div>
	</div>
	
	<div class="hr"></div>
	
</div>
 <div id="feedback"></div>
<script type="text/javascript"
        src="/js/jquery-1.11.1.min.js"></script>
 
<script type="text/javascript" src="/js/home.js"></script>
<script type="text/javascript" src="/js/buystocks.js"></script>
 
</body>
</html>