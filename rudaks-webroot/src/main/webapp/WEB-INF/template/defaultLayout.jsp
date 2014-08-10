<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
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
    <meta content="Rudaks.co.kr에 오신 것을 환영합니다." property="og:title" />
    <meta content="Java관련 프로그래밍 사이트" property="og:description" />
    <meta content="http://www.rudaks.co.kr/static/images/favicon.ico" property="og:image" />
    <meta content="http://rudaks.co.kr" property="og:url" />
    <meta content="website" property="og:type" />
    <meta content="Rudaks.co.kr" property="og:site_name" />
    <meta name="google-site-verification" content="2-cT11turiVFaoJlpAkcd1eXrTKNB1F2m37CVkOLgMM" />
 
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1">
	<title><tiles:getAsString name="title"/></title>
	<%@ include file="/WEB-INF/jsp/include/commonScript.jsp" %>
  </head>
  
<!--[if IE 7 ]> <body class="ie7"> <![endif]-->
<!--[if IE 8 ]> <body class="ie8"> <![endif]-->
<!--[if IE 9 ]> <body class="ie9"> <![endif]-->
<!--[if !IE]>--> <body> <!--<![endif]-->

<tiles:insertAttribute name="body" />

</body>
</html>