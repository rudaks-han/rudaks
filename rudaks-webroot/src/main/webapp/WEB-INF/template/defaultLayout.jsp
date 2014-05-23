<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<title><tiles:getAsString name="title"/></title>
<%@ include file="/WEB-INF/views/include/commonScript.jsp" %>
</head>   
<html>
<body>

<tiles:insertAttribute name="body" />

</body>
</html>