<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Download Chat History</title>
</head>

<body bgcolor="#FFFFFF">

<form method="POST" action='ChatServlet' name="history">
    <input type="hidden" name="action" value="history">
    Format: <select name="format">
    <option value="txt">.txt</option>
    <option value="json">.json</option>
    </select>
    <input type="submit">
</form>
</body>
</html>