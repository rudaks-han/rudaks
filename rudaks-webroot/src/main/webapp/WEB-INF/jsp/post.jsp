<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include/commonLib.jsp" %>
				<div class="col-lg-8">
					<div>
						<h2>${postForm.title}</h2>
						<p class="blog-post-meta">
							${postForm.createdDateDisplay} by <a href="#">${postForm.username}</a> | <a href="#">${postForm.categoryName}</a>
						</p>
						<div class="post-content">
							<p>
								${postForm.content}
							</p>
						</div>
						<c:forEach var="attach" items="${postForm.attachFileList}">
							<a type="button" href="/download?filename=${attach.fileName}&filepath=${attach.filePath}" class="btn btn-default btn-xs">${attach.fileName}</a>
						</c:forEach>
					</div>
					
					<div style="float:right">
					<c:if test="${ !empty sessionScope.loginInfo }">
						<a href="/post-modify/${postForm.id}" id="btnModify" class="btn btn-primary">수정</a>
						<a id="btnDelete" class="btn btn-primary">삭제</a>
						<script>
						$(function() {
						    $('#btnDelete').on('click', function() {
						      	var check = confirm("해당 글을 삭제하시겠습니까?");
						      	if (check)
						      	{
						      	    document.write('<form method="post" name="deleteForm" action="/post-delete/${postForm.id}">')
						      	    document.write('</form>');
						      	    document.deleteForm.submit();
						      	}
						    })
						})
						</script>
					</c:if>
					</div>
					
					
					<div style="margin-top:50px;">
						<div id="disqus_thread"></div>
						<script src="/static/js/disqus.js"></script>
					</div>
				</div>

				
			
