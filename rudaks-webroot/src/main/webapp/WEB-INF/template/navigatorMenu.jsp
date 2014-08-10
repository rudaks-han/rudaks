<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/include/commonLib.jsp" %>
				<div class="col-lg-4">
					<div class="list-group">
						<div class="list-group-item active">Category</div>
						<c:forEach var="entry" items="${categoryListByCount}" varStatus="loop">
		              		<a href="/list/${entry.category}" class="list-group-item">${entry.name} (${entry.cnt})</a>
		              	</c:forEach>
					</div>
					<div class="panel panel-success">
						<div class="panel-heading">
							<h3 class="panel-title">Recent posts</h3>
						</div>
	
						<div class="panel-body">
							
							<ol class="list-unstyled">
							<c:forEach var="entry" items="${recentPostList}" varStatus="loop">
								<li class="ellipsis"><a href="/post/${entry.id}">${entry.title}</a></li>
							</c:forEach>
							</ul>
		
						</div>
					</div>			
					
					<div class="panel panel-success">
						<div class="panel-heading">
							<h3 class="panel-title">Links</h3>
						</div>
	
						<div class="panel-body">
							<ul>
								<li>SECUidC <a href="http://www.secuidc.com/" target="_blank">http://www.secuidc.com/</a></li>
							</ul>					
						</div>
					</div>			
				</div>