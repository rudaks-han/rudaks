<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include/commonLib.jsp" %>
<script>
$(function() {
   $('.panel-title').hover(function() {
       $(this).find('.btn-modify').removeClass('hide');
       $(this).find('.btn-delete').removeClass('hide');
   }, function() {
       $(this).find('.btn-modify').addClass('hide');
       $(this).find('.btn-delete').addClass('hide');
   })
   
   $('.btn-modify').on('click', function() {
       location.href = '/guestbook/modify/' + $(this).attr('entryId');
   });
    
   $('.btn-delete').on('click', function() {
       location.href = '/guestbook/delete/' + $(this).attr('entryId');
   });
});

function toggleReplyForm(id)
{
    $('#reply_form_' + id).toggle();
}
</script>
	<div class="col-lg-8">		
		<div>
            <div class="well bs-component">
              <sf:form name="postForm" id="postForm" method="post" class="form-horizontal" modelAttribute="guestbookForm">
                <fieldset>
                  <legend>Guestbook</legend>
                  <div class="form-group">
                    <label for="inputname" class="col-lg-2 control-label">Username</label>
                    <div class="col-lg-10">
                    	<sf:input class="form-control" path="username" id="username" placeholder="Username" />
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">Email</label>
                    <div class="col-lg-10">
                      	<sf:input class="form-control" path="email" id="email" placeholder="Email" />
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">Password</label>
                    <div class="col-lg-10">
                    	<sf:password class="form-control" path="password" id="password" placeholder="Password" />
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputTitle" class="col-lg-2 control-label">Comment</label>
                    <div class="col-lg-10">
                      <sf:textarea class="form-control" rows="3" path="comment" id="comment" placeholder="Comment" />
                    </div>
                  </div>
                 
	                  
                  <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2 text-right">
                      <!-- <button type="button" id="btn-submit" class="btn btn-primary">등록</button> -->
                      <input type="submit" id="btn-submit" class="btn btn-primary" value="등록">
                    </div>
                  </div>
                </fieldset>
              </sf:form>
            <div id="source-button" class="btn btn-primary btn-xs" style="display: none;">&lt; &gt;</div></div>
          </div>
          
          <c:forEach var="entry" items="${guestbookList}" varStatus="loop">
          <div <c:if test="${entry.id == entry.ref}">id="post-${entry.ref}" </c:if> class="panel panel-primary <c:if test="${entry.id != entry.ref}">guestbook-reply-margin</c:if>">
			  <div class="panel-heading">
			    <h3 class="panel-title">
						<div class="pull-left">${entry.createdDateDisplay } by ${entry.username }</div>
						<div class="pull-right">
							<button type="button" class="btn btn-link btn-xs btn-modify hide" entryId=${entry.id}>Modify</button>
							<button type="button" class="btn btn-link btn-xs btn-delete hide" entryId=${entry.id}>Delete</button>
							<c:if test="${entry.id == entry.ref}">
								<button type="button" class="btn btn-link btn-xs btn-reply" entryId=${entry.id} onclick="toggleReplyForm('${entry.id}')">Reply</button>
							</c:if>
						</div>&nbsp;
			    </h3>
			    
			  </div>
			  
			  <div class="panel-body">
			  <%pageContext.setAttribute("crlf", "\r\n");%>
			  	<c:out value="${fn:replace(entry.comment, crlf, '<br/>')}" escapeXml="false"/>
			  </div>
			</div>
			
			<div id="reply_form_${entry.id}" style="display:none;">
				<form name="postForm" id="postForm" method="post" class="form-horizontal">
				<input type="hidden" name="ref" value="${entry.id}" />
	                <fieldset>
	                  <legend>Reply</legend>
	                  <div class="form-group">
	                    <label for="inputname" class="col-lg-2 control-label">Username</label>
	                    <div class="col-lg-10">
	                    	<input type="text" class="form-control" name="username" id="username" placeholder="Username" />
	                    </div>
	                  </div>
	                  <div class="form-group">
	                    <label for="inputEmail" class="col-lg-2 control-label">Email</label>
	                    <div class="col-lg-10">
	                      	<input type="text" class="form-control" name="email" id="email" placeholder="Email" />
	                    </div>
	                  </div>
	                  <div class="form-group">
	                    <label for="inputEmail" class="col-lg-2 control-label">Password</label>
	                    <div class="col-lg-10">
	                    	<input type="password" class="form-control" name="password" id="password" placeholder="Password" />
	                    </div>
	                  </div>
	                  <div class="form-group">
	                    <label for="inputTitle" class="col-lg-2 control-label">Comment</label>
	                    <div class="col-lg-10">
	                      <textarea class="form-control" rows="3" name="comment" id="comment" placeholder="Comment"></textarea>
	                    </div>
	                  </div>
	                 
		                  
	                  <div class="form-group">
	                    <div class="col-lg-10 col-lg-offset-2 text-right">
	                      <input type="submit" id="btn-submit" class="btn btn-primary" value="등록">
	                      <button type="button" id="btn-cancel" class="btn btn-primary" onclick="toggleReplyForm('${entry.id}')">취소</button>
	                    </div>
	                  </div>
                  </div>
                </fieldset>
              </form>
			</c:forEach>
			
			<div class="pager pagination">
						${navLinkHtml}
					</div>
			
		</div>