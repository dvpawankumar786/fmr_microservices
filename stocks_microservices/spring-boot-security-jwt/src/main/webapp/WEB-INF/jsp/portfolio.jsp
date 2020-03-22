<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ page import="java.util.List" %>
		<%@ page import="java.util.ArrayList" %>
		<%@ page import="com.security.jwt.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Portfolio</title>
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
List<PortfolioResponseDto> portslist = (List<PortfolioResponseDto>) sessionsa.getAttribute("portlist");
%>
	<div style="text-align:center" class="group">
				<input type="submit" id="home1" class="button" value="home"/>
			</div>

			<div class="sign-up-htm">
				<div>
				<table  border="1" cellpadding="0" cellspacing="0">
				<tr>
				<td>portfolioId</td>
				<td>stocksId</td>
				<td>timestamp</td>
				
				</tr>
				
    <%
       if(null!=portslist && portslist.size()>0 && portslist.get(0).getTransFlag()!=null && portslist.get(0).getTransFlag().equalsIgnoreCase("S") )
       {
    	   
        for(PortfolioResponseDto p:portslist)
        {
            %>
                <tr>
                 
                     <td><%= p.getPortfolioId()%></td><td><%= p.getStockId()%></td><td><%= p.getTimestamp()%></td>
                </tr>
            <% 
        }
       }
       else if(null!=portslist && portslist.size()>0 && portslist.get(0).getTransFlag()!=null && portslist.get(0).getTransFlag().equalsIgnoreCase("F") )
       {%>
    	   <div><p><%=portslist.get(0).getTransStatus()%></p></div>
       <%}
    
    %>
</table>  
			</div>
	
 <script type="text/javascript"
        src="/js/jquery-1.11.1.min.js"></script>
 
<script type="text/javascript" src="/js/home.js"></script>
</body>
</html>