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
						
						<script type="text/javascript">var addthis_config = {"data_track_addressbar":false};</script>
						<script type="text/javascript" src="//s7.addthis.com/js/300/addthis_widget.js#pubid=ra-53d6e026320d3615"></script>
						<div class="addthis_native_toolbox" style="margin-top:50px;" data-title="${entry.title}" data-url="http://rudaks.co.kr/post/${entry.id}"></div>
												
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