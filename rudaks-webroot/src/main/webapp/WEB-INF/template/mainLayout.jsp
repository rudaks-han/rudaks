<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta http-equiv="Content-Script-Type" content="text/javascript" />
    <meta http-equiv="Content-Style-Type" content="text/css" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <link rel='shortcut icon' type='image/x-icon' href='/static/images/favicon.ico' />
    <meta content="java 관련 프로그래밍 사이트" name="description" />
    <meta content="rudaks, 자바, Java, Servlet, JSP, JDBC, Spring, iBatis, Framework, Linux, Unix, JavaScript, HTML, CSS, Web2.0, Ajax, DB, 데이타베이스, HTML5, Oracle" />
    <meta content="<c:if test="${empty pageTitle}"><tiles:getAsString name="title"/></c:if><c:if test="${not empty pageTitle}"><c:out value="${pageTitle}" escapeXml="true" /></c:if>" property="og:title" />
    <meta content="Java관련 프로그래밍 사이트" property="og:description" />
    <meta content="http://www.rudaks.pe.kr/static/images/favicon.ico" property="og:image" />
    <meta content="http://rudaks.pe.kr" property="og:url" />
    <meta content="website" property="og:type" />
    <meta content="Rudaks.pe.kr" property="og:site_name" />
    <meta name="google-site-verification" content="e-SDTpJcfy5zOx9XUozWr_SuANhK2rrqNUzBFRr9VqY" />
 
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1">
	<title><c:if test="${empty pageTitle}"><tiles:getAsString name="title"/></c:if><c:if test="${not empty pageTitle}"><c:out value="${pageTitle}" escapeXml="true" /></c:if></title>
	<%@ include file="/WEB-INF/jsp/include/commonScript.jsp" %>
  </head>
  
<!--[if IE 7 ]> <body class="ie7"> <![endif]-->
<!--[if IE 8 ]> <body class="ie8"> <![endif]-->
<!--[if IE 9 ]> <body class="ie9"> <![endif]-->
<!--[if !IE]>--> <body> <!--<![endif]-->
	<div class="navbar navbar-default navbar-fixed-top">
		<tiles:insertAttribute name="header" />
	</div>
	
	<div class="container">
        <div class="bs-docs-section">
        	<div class="row">
	        	<tiles:insertAttribute name="body" />
        		<tiles:insertAttribute name="navigatorMenu" />
            </div>
        </div>
    </div>
    <tiles:insertAttribute name="footer" />
</body>
</html>
