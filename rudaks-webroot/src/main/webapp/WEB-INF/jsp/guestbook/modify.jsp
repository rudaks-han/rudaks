<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include/commonLib.jsp" %>

	<div class="col-lg-8">		
		<div>
            <div class="well bs-component">
              <sf:form name="postForm" id="postForm" method="post" class="form-horizontal" modelAttribute="guestbookForm">
              <sf:hidden path="id" />
                <fieldset>
                  <legend>Guestbook</legend>
                  <div class="form-group">
                    <label for="inputname" class="col-lg-2 control-label">Username</label>
                    <div class="col-lg-10">
                    	<sf:input class="form-control" path="username" id="username" placeholder="Username" disabled="true" />
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">Email</label>
                    <div class="col-lg-10">
                      	<sf:input class="form-control" path="email" id="email" placeholder="Email" disabled="true"/>
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
                      <input type="submit" id="btn-submit" class="btn btn-primary" value="수정">
                      <input type="button" id="btn-submit" class="btn btn-primary" value="취소" onclick="history.back();">
                    </div>
                  </div>
                </fieldset>
              </sf:form>
            <div id="source-button" class="btn btn-primary btn-xs" style="display: none;">&lt; &gt;</div></div>
          </div>
          
			
		</div>