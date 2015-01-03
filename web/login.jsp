<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
<html>
<head>
  <title>JSP Chat</title>
</head>

<body bgcolor="#FFFFFF">

<form method="POST" action='ChatServlet' target="_top" name="loginForm">
  <input type="hidden" name="action" value="login">
  Nickname: <input type="text" name="nickname">
  <input type="submit">
</form>

</body>
</html>