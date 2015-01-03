<%@page contentType="text/html; charset=UTF-8" %>
<% if (session.getAttribute("nickname") == null) {
  response.sendRedirect("login.jsp");
  return;
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
<head>
  <title>JSP Chat</title>
</head>
<frameset rows="1*,4*">
  <frame name="post" src="post.jsp" scrolling="no" title="Post message">
  <frame name="chat" src="ChatServlet" scrolling="yes" title="Chat">
</frameset>
</html>