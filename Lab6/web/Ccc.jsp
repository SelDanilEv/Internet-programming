<%@ page import="by.belstu.CBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lab6</title>
</head>
<body>
<%
    CBean cBean = (CBean) request.getServletContext().getAttribute("atrCBean");
%>
<div>
        <%= "Value1: " + cBean.getValue1()%> <br>
        <%= "Value2: " + cBean.getValue2()%> <br>
        <%= "Value3: " + cBean.getValue3()%>
</div>
</body>
</html>