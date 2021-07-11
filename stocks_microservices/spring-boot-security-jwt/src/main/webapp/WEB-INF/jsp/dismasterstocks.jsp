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
String pid = (String) sessionsa.getAttribute("pid");
String sid = (String) sessionsa.getAttribute("sid");
response.setHeader("Authorization","Bearer "+token);
List<Stocks> stockslist = (List<Stocks>) sessionsa.getAttribute("masterstockslist");
%>
<div class="hr"></div>
 
<div id="formmasterstocks" class="login-wrap">
<input type="hidden" id="sid" name="sid" value=<%=sid%>>

	<div class="login-html">
	<input type="hidden" id="token" name="token" value=<%=token%>>
	<div style="text-align:right" class="group">
				<input type="submit" id="home1" class="button" value="home"/>
			</div>
	<div><h2 style="color:#784">Buy Stocks</h2></div>
		<div>
				<table  border="1" cellpadding="0" cellspacing="0" id="myTable">
				<tr>
				<td>portfolioId</td>
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
                 
                     <td class="pid"><%=pid%></td><td class="symbol"><%= s.getSymbol()%></td><td class="type"><%= s.getType()%></td><td  class="lastDividend"><%= s.getLastDividend()%></td><td  class="fixedDividend"><%= s.getFixedDividend()%></td><td  class="parValue"><%= s.getParValue()%></td><td  id="tickerPrice"><%= s.getTickerPrice()%></td><td><button class="btnSelect">buy stock</button></td>
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
<!-- <div class="group">
					<input type="submit" id="buystockid" class="button" value="Buy This Stock" />
				</div> -->
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