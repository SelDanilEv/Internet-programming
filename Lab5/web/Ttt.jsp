<%@ taglib prefix="lib" uri="taglib.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lab 5</title>
</head>
<body>
<lib:dossier action="Ttt">
    <lib:surname name="surname"/>
    <lib:lastname name="lastname"/>
    <lib:sex name="sex"/>
    <br/>
    <lib:submit type="ok"/>
    <lib:submit type="cancel"/>
</lib:dossier>
</body>
</html>