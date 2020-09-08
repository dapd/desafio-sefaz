<%
    session.setAttribute("SESSION_IDENTIFICADOR_LOGIN", null);
	session.setAttribute("SESSION_IDENTIFICADOR_NOME", null);
    session.invalidate();
    response.sendRedirect("index.jsp");
%>