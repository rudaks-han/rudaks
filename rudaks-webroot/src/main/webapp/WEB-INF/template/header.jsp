<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/include/commonLib.jsp" %>
			<div class="container">
            <div class="navbar-header">
                <a href="/" class="navbar-brand">Rudaks.co.kr</a>
                <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target="#navbar-main">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="navbar-collapse collapse" id="navbar-main">
                <ul class="nav navbar-nav">
		            <li>
		              <a href="javascript:;" id="menu-about">About</a>
		            </li>
		            <li class="dropdown">
		              <a class="dropdown-toggle" data-toggle="dropdown" href="#" id="menu-category">Category <span class="caret"></span></a>
		              <ul class="dropdown-menu" aria-labelledby="Category">
		              	<c:forEach var="entry" items="${categoryList}" varStatus="loop">
		              		<li><a tabindex="-1" href="/list/${entry.category}">${entry.name}</a></li>
		              	</c:forEach>
		              </ul>
		            </li>
		            <li>
		              <a href="/guestbook" id="menu-guestbook">Guestbook</a>
		            </li>
		            <c:if test="${ !empty sessionScope.loginInfo }">
		            <li>
		              <a href="/post-new" id="menu-post-new">New</a>
		            </li>
		            </c:if>
	          	</ul>
	          	<form class="navbar-form navbar-left">
                    <input type="text" class="form-control col-lg-8" placeholder="Search">
                </form>
                  
                <ul class="nav navbar-nav navbar-right">
                <c:if test="${ empty sessionScope.loginInfo }">   
                    <li><a href="/login">Login</a></li>
                </c:if>
                <c:if test="${ !empty sessionScope.loginInfo }">
                	<li><a href="/logout">Logout</a></li>
                </c:if>
                </ul>
            </div>
        </div>
	
	