<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta http-equiv="Content-Script-Type" content="text/javascript" />
    <meta http-equiv="Content-Style-Type" content="text/css" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <link rel='shortcut icon' type='image/x-icon' href='/resources/images/favicon.ico' />
    <meta content="<c:out value="${pageTitle}" escapeXml="true" />" name="description" />
    <meta content="File protection, File security, cloud file encryption, cloud file security, Dropbox security, Dropbox protection, File control, Privacy protection, Contents protection, Personal file protection, Safe secret, safe privacy, Persistent file protection, Keep private files private, Keep confidential files confidential, Permission control, Control files, Audit file, Track files, Secure file sharing, Secure collaboration, File encryption, Cloud storage protection, Dropbox encryption, windows, iphone, ipad, android" name="keywords" />
    <meta content="<tiles:getAsString name="title"/> | DigitalQuick" property="og:title" />
    <meta content="<c:out value="${pageTitle}" escapeXml="true" />" property="og:description" />
    <meta content="https://s3.amazonaws.com/static.digitalquick.com/faq/images/DQ_64.png" property="og:image" />
    <meta content="${WEB_URL}${requestScope["javax.servlet.forward.request_uri"]}" property="og:url" />
    <meta content="website" property="og:type" />
    <meta content="DigitalQuick" property="og:site_name" />
    <meta content="443627559011060" property="fb:app_id" />
    <meta content="summary" name="twitter:card" />
    <meta content="@digitalquicktwt" name="twitter:site" />
    <meta content="${WEB_URL}${requestScope["javax.servlet.forward.request_uri"]}" name="twitter:url" />
    <meta content="<tiles:getAsString name="title"/> | DigitalQuick" name="twitter:title" />
    <meta content="<c:out value="${pageTitle}" escapeXml="true" />" name="twitter:description" />
    <meta content="https://s3.amazonaws.com/static.digitalquick.com/faq/images/DQ_64.png" name="twitter:image" />
    <meta content="https://s3.amazonaws.com/static.digitalquick.com/faq/images/DQ_64.png" name="msapplication-TileImage" />
    <meta name="google-site-verification" content="sk1n7_M5vCiN51S2pml2YHC0rI-akjRrr70VzCH78Js" />
    <meta name="msvalidate.01" content="23A6C6AAB817B801A83A4566E95E00D7" />
    <title><c:out value="${pageTitle}" escapeXml="true" /> | DigitalQuick</title>
    <%@ include file="/WEB-INF/views/include/commonScript.jsp" %>
</head>
<!--[if IE 7 ]> <body class="ie7"> <![endif]-->
<!--[if IE 8 ]> <body class="ie8"> <![endif]-->
<!--[if IE 9 ]> <body class="ie9"> <![endif]-->
<!--[if !IE]>--> <body> <!--<![endif]-->
	<div class="wrap">	
		<div class="header">
			<tiles:insertAttribute name="header" />
		</div>
		<div class="container">
			<tiles:insertAttribute name="leftMenu" />	        
	        <tiles:insertAttribute name="body" />
        </div>        
        <div class="footer">
            <tiles:insertAttribute name="footer" />
        </div>
    </div>
</body>
</html>