<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <title>Rudaks.co.kr</title>

    <!-- <meta name="description" content="CloudStack is open source cloud computing software for creating, managing, and deploying infrastructure cloud services">

    <meta itemprop="name" content="Apache Cloudstack">
    <meta itemprop="description" content="CloudStack is open source cloud computing software for creating, managing, and deploying infrastructure cloud services">
    <meta itemprop="image" content="https://cloudstack.apache.org/images/monkey-144.png">

    <meta property="og:title" content="Apache Cloudstack">
    <meta property="og:description" content="CloudStack is open source cloud computing software for creating, managing, and deploying infrastructure cloud services">
    <meta property="og:site_name" content="Apache Cloudstack"/>
    <meta property="og:image" content="https://cloudstack.apache.org/images/monkey-144.png">

    <meta name="twitter:card" content="summary">
    <meta name="twitter:title" content="Apache Cloudstack">
    <meta name="twitter:description" content="CloudStack is open source cloud computing software for creating, managing, and deploying infrastructure cloud services">
    <meta name="twitter:image:src" content="https://cloudstack.apache.org/images/monkey-144.png">
 -->
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1">

    <link href="/static/css/bootstrap.css" rel="stylesheet" media="screen">
    <link href="/static/css/font-awesome.css" rel="stylesheet">
    <link href="/static/css/bootswatch.min.css" rel="stylesheet">

    <link rel="shortcut icon" href="/images/favicon.ico">
    <link rel="icon" href="/images/favicon.ico">

    <!-- Twitter Bootstrap and jQuery after this line. -->
    <script src="https://code.jquery.com/jquery-latest.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script>
        $('.dropdown-toggle').dropdown();
        $('.nav-collapse').collapse();
    </script>
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
        		<tiles:insertAttribute name="rightMenu" />
            	<tiles:insertAttribute name="footer" />
            </div>
        </div>
    </div>
</body>
</html>
