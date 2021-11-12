<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lab6</title>
</head>
<body>
    <h1>Index</h1>
    <%=getServletContext().getInitParameter("URL1")%>
    <br />
    <%=getServletContext().getInitParameter("URL2")%>
    <br />
    <%=getServletContext().getInitParameter("URL3")%>
</body>
</html>