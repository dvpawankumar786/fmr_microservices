<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ page import="java.util.List" %>
		<%@ page import="java.util.ArrayList" %>
		<%@ page import="com.security.jwt.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Stocks</title>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	  <link rel="stylesheet" type="text/css"
          href="/css/styles.css"/>
<style type="text/css">
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
th, td {
  padding: 15px;
}</style>
</head>
<body>
<%
HttpSession sessionsa = request.getSession(false);
List<Stocks> stockslist = (List<Stocks>) sessionsa.getAttribute("stockslist");
%>
<div class="group" style="text-align:center">
				<input type="submit" id="home2" class="button" value="home"/>
			</div>
			<div class="sign-up-htm">
				<div>
				<table  border="1" cellpadding="0" cellspacing="0">
				<tr>
				<td>symbol</td>
				<td>stocksId</td>
				<td>portfolioId</td>
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
                 
                     <td><%= s.getSymbol()%></td><td><%= s.getStocksId()%></td><td><%= s.getPortfolioId()%></td><td><%= s.getType()%></td><td><%= s.getLastDividend()%></td><td><%= s.getFixedDividend()%></td><td><%= s.getParValue()%></td><td><%= s.getTickerPrice()%></td>
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
	
 <script type="text/javascript"
        src="/js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="/js/home.js"></script>
</body>
</html>