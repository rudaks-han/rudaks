<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include/commonLib.jsp" %>
				<div class="col-lg-8">
					<c:forEach var="entry" items="${postList}" varStatus="loop">
					<div class="blog-post">
						<h2 class="blog-post-title"><a href="/post/${entry.id}">${entry.title}</a></h2>
						<p class="blog-post-meta">
							${entry.createdDateDisplay} by <a href="#">${entry.username}</a> | <a href="#">${entry.categoryName}</a>
						</p>
							${entry.content}
						</p>
						
												
					</div>
					</c:forEach>
					<c:if test="${empty postList}">
						<div>등록된 글이 없습니다.</div>
					</c:if>
					
					<c:if test="${not empty postList}">
						<div class="pager pagination">
							${navLinkHtml}
						</div>
					</c:if>
					
				</div>