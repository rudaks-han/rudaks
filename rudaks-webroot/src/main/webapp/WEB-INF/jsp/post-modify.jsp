<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include/commonLib.jsp" %>

<!-- <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/codemirror/3.20.0/codemirror.min.css" />
<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/codemirror/3.20.0/theme/monokai.min.css" />
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/codemirror/3.20.0/codemirror.min.js"></script>
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/codemirror/3.20.0/mode/xml/xml.min.js"></script>
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/codemirror/2.36.0/formatting.min.js"></script> -->

<link href="/static/css/summernote.css" rel="stylesheet">
<style>
.note-editable {background-color:#fff;}
</style>
<script type="text/javascript" src="/static/js/summernote.min.js"></script>

<script>
$(function() {
	$('.summernote').summernote({
		airMode: true,
		//lang: 'ko-KR', 
		codemirror: { // codemirror options
   			theme: 'cosmo'
  		},
		height: 400,                 // set editor height
		minHeight: null,             // set minimum height of editor
		maxHeight: null,             // set maximum height of editor
		focus: false,                 // set focus to editable area after initializing summernote
	});
	
	// 첨부파일이 있으면 표시해준다.
	//$('.attach-result')
	
	$('.btn-file :file').on('change', function() {
	    var input = $(this),
	        numFiles = input.get(0).files ? input.get(0).files.length : 1,
	        label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
	    input.trigger('fileselect', [numFiles, label]);
	});
	
	var files = [];
	
	$('.btn-file :file').on('fileselect', function(e, numFiles, label) {
        console.log(numFiles);
        console.log(label);
        
        //$('#postForm').find('#filePath').val('');
    	//$('.attach-result').html('');
    	
        if (numFiles > 0)
        {
	        var files = e.target.files || e.dataTransfer.files;
	    	for (var i = 0, f; f = files[i]; i++) 
	    	{
	    		uploadFile(f);
	    	}
        }
    });
	
	function uploadFile(file)
	{
		var data = new FormData();
		data.append('attachFile', file);
		
		$.ajax({
		    url: '/upload-attach',
		    data: data,
		    cache: false,
		    contentType: false,
		    processData: false,
		    type: 'POST',
		    success: function(data)
		    {
				if (data.success)
		    	{
					if ($('#postForm').find('#filePath').val() == '')
					{
						$('#postForm').find('#filePath').val(data.filePath);
						$('#postForm').find('#fileName').val(data.fileName);
						$('#postForm').find('#fileSize').val(data.fileSize);
					}
					else
					{
						$('#postForm').find('#filePath').val($('#postForm').find('#filePath').val() + ',' + data.filePath);
						$('#postForm').find('#fileName').val($('#postForm').find('#fileName').val() + ',' + data.fileName);
						$('#postForm').find('#fileSize').val($('#postForm').find('#fileSize').val() + ',' + data.fileSize);
					}
					
					displayAttachList();
					
		    	}
		    }
		});
	} 
	
	$('#btn-submit').on('click', function() {
		var sHTML = $('.summernote').code();
		var inForm = $('#postForm');
		inForm.find('#_content').val(sHTML);
		inForm.submit();
	});
	
	$('#btn-cancel').on('click', function() {
	  location.href = '/post/' + $('#postForm').find('#id').val();
	});
	displayAttachList();
});


// $('.summernote').code(sHTML);
</script>

		<div class="col-lg-8">
            <div class="well bs-component">
              <sf:form name="postForm" id="postForm" method="post" class="form-horizontal" modelAttribute="postForm">
              <sf:hidden path="id" id="id" />
              <sf:hidden path="filePath" id="filePath" />
              <sf:hidden path="fileName" id="fileName" />
              <sf:hidden path="fileSize" id="fileSize" />
                <fieldset>
                  <legend>New post in JSP</legend>
                  <div class="form-group
                  <s:bind path="category">
                  	<c:if test="${status.error}">has-error</c:if>
                  </s:bind>
                  ">
                    <label for="inputname" class="col-lg-2 control-label">Category</label>
                    <div class="col-lg-10">
                      <sf:select class="form-control" path="category" id="category">
		              	<c:forEach var="entry" items="${categoryList}" varStatus="loop">
		              		<sf:option value="${entry.category}">${entry.name}</sf:option>
		              	</c:forEach>
                      </sf:select>
                    </div>
                  </div>
                  <div class="form-group
                  <s:bind path="username">
                  	<c:if test="${status.error}">has-error</c:if>
                  </s:bind>
                  ">
                    <label for="inputname" class="col-lg-2 control-label">Name</label>
                    <div class="col-lg-10">
                      <sf:input class="form-control" path="username" id="username" placeholder="Name" />
                    </div>
                  </div>
                  <div class="form-group
                  <s:bind path="email">
                  	<c:if test="${status.error}">has-error</c:if>
                  </s:bind>
                  ">
                    <label for="inputEmail" class="col-lg-2 control-label">Email</label>
                    <div class="col-lg-10">
                      <sf:input class="form-control" path="email" id="email" placeholder="Email" />
                    </div>
                  </div>
                  <div class="form-group
                  <s:bind path="title">
                  	<c:if test="${status.error}">has-error</c:if>
                  </s:bind>
                  ">
                    <label for="inputTitle" class="col-lg-2 control-label">Title</label>
                    <div class="col-lg-10">
                      <sf:input class="form-control" path="title" id="title" placeholder="Title" />
                    </div>
                  </div>
                 <div class="form-group
                 <s:bind path="content">
                  	<c:if test="${status.error}">has-error</c:if>
                  </s:bind>
                 ">
                    <label for="textArea" class="col-lg-2 control-label"></label>
                    <div class="col-lg-10 summernote-layer">
                      <div class="summernote">${postForm.content}</div>
                      <input type="hidden" name="content" id="_content" />
                    </div>
                  </div>
                  <div class="file-layer">
	                  <div class="form-group">
	                    <label for="inputTitle" class="col-lg-2 control-label">File</label>
	                    <div class="col-lg-10">
        					<span class="btn btn-default btn-file">
							    Browse <input type="file" multiple>
							</span>
							<span class="attach-result"></span>
	                    </div>
	              </div>
                  <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2 text-right">
                      <button type="button" id="btn-submit" class="btn btn-primary">수정</button>
                      <button type="button" id="btn-cancel" class="btn btn-default">취소</button>
                    </div>
                  </div>
                </fieldset>
              </sf:form>
            <div id="source-button" class="btn btn-primary btn-xs" style="display: none;">&lt; &gt;</div></div>
          </div>


