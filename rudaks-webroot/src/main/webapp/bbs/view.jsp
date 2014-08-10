<%
	
	String seq = request.getParameter("seq");
	if (seq != null)
	{
		response.sendRedirect("/post/" + seq + "?forward=1");
		return;
	}
%>