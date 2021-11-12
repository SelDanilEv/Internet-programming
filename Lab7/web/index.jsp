<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lab7</title>
</head>
<body>
<h1>Index</h1>
<%
    String url1 = getServletContext().getInitParameter("URL1");
    String url2 = getServletContext().getInitParameter("URL2");
%>
<br /> <%=url1%>
<br /> <%=url2%>
</body>
</html>