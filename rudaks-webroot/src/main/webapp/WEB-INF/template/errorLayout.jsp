<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <title><tiles:getAsString name="title"/></title>
    <%@ include file="/WEB-INF/views/include/commonScript.jsp" %>
</head>
<!--[if IE 7 ]> <body class="ie7"> <![endif]-->
<!--[if IE 8 ]> <body class="ie8"> <![endif]-->
<!--[if IE 9 ]> <body class="ie9"> <![endif]-->
<!--[if !IE]>--> <body> <!--<![endif]-->
	<div class="wrap">	
		<div class="header no_line">
			<tiles:insertAttribute name="header" />
		</div>		
		<div class="container">
	        <tiles:insertAttribute name="body" />
        </div>		        
		<div class="footer">
            <tiles:insertAttribute name="footer" />
        </div>
    </div>
</body>
</html>