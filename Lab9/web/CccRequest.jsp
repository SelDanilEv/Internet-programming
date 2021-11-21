<%@ page import="by.belstu.Bean.CBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lab9</title>
</head>
<body>
<%
    CBean cBean = (CBean) request.getAttribute("atrCBean");
%>
<div>
    <div>
        <%= "Value1: " + cBean.getValue1()%>
    </div>
    <div>
        <%= "Value2: " + cBean.getValue2()%>
    </div>
    <div>
        <%= "Value3: " + cBean.getValue3()%>
    </div>
</div>
</body>
</html>