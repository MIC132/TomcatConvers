<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
<html>
<head>
  <title>Chat</title>
</head>

<body bgcolor="#FFFFFF">

<form method="POST" action='ChatServlet' name="postForm">
  <input type="hidden" name="action" value="post">
  Message: <input type="text" name="message">
  <input type="submit">
</form>

<br>
<%
  String serverName = request.getServerName();
  if ("localhost".equals(serverName)) {
    serverName = "127.0.0.1";
  } else if ("127.0.0.1".equals(serverName)) {
    serverName = "localhost";
  }

  String chatUrl = request.getScheme() + "://" + serverName + ":" + request.getServerPort() + request.getContextPath() + request.getServletPath();

  // strip "post.jsp" from the address
  chatUrl = chatUrl.substring(0, chatUrl.lastIndexOf('/') + 1);
%>
<a target="_blank" href="<%=chatUrl %>">Click to open a new chat window</a><br/>
<em>Note</em>: To avoid hitting the limit on the count of simultaneous
connections to the same host, imposed by the
<a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec8.html#sec8.1.4">HTTP specification</a>,
the second chat window should be opened using a different URL, e.g. with
an IP address instead of the host name.
</body>
</html>