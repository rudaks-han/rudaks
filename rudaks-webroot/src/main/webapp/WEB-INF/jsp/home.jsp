<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include/commonLib.jsp" %>
				<div class="col-lg-8">
					<c:forEach var="entry" items="${postList}" varStatus="loop">
					<div class="blog-post">
						<h2 class="blog-post-title"><a href="/post/${entry.id}">${entry.title}</a></h2>
						<p class="blog-post-meta">
							${entry.createdDateDisplay} by <a href="#">${entry.username}</a> | <a href="#">${entry.categoryName}</a>
						<div class="post-content">
							<p>
								${entry.content}
							</p>
						</div>
						<c:forEach var="attach" items="${entry.attachFileList}">
							<a type="button" href="/download?filename=${attach.fileName}&filepath=${attach.filePath}" class="btn btn-default btn-xs">${attach.fileName}</a>
						</c:forEach>
						
					</div>
					
					<div class="btn-addthis">
						
						<span style="float:right"><a href="/post/${entry.id}#disqus_thread" data-disqus-identifier="post_${entry.id}">comment</a></span>
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
				<%@ include file="/WEB-INF/jsp/include/disquscount.jsp" %>