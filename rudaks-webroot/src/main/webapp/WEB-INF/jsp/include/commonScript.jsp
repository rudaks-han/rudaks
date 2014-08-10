<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include/commonLib.jsp" %>	
	<link href="/static/css/bootstrap.css" rel="stylesheet" media="screen">
    <!-- <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet"> --> 
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/bootswatch.min.css" rel="stylesheet">
    <link href="/static/css/rudaks.css" rel="stylesheet">
   
    <!-- Twitter Bootstrap and jQuery after this line. -->
    <script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.1/js/bootstrap.min.js"></script>
    <script src="/static/js/notify.min.js"></script>
    <script src="/static/js/rudaks.js"></script> 
    <script src="http://www.google-analytics.com/urchin.js" type="text/javascript"></script>
	<script type="text/javascript">
	_uacct = "UA-1637926-1";
	urchinTracker();
	</script>
    
    <script>
    $(function() {
        $('.dropdown-toggle').dropdown();
        $('.nav-collapse').collapse();
        
        var uri = window.location.pathname;
        if (uri.match("^/list/")) 
            $('#menu-category').parent('li').addClass('active');
        else if (uri.match("^/guestbook")) 
            $('#menu-guestbook').parent('li').addClass('active');
        else if (uri.match("^/post-new")) 
            $('#menu-post-new').parent('li').addClass('active');
        
        $('#menu-about').on('click', function() {
        	modal.show({'title' : 'title', 'contents' : '준비중입니다.'})
        })
    })
    </script>
    
    <c:if test="${not empty notifyMessage}">
    <script>
	$(function() {
		<c:forEach var="notify" items="${notifyMessage}">
			var options = {
				className : "${notify.className}"
			}
			<c:if test="${notify.element != null}">
				notify('${notify.message}', options, '${notify.element}');
			</c:if>
			<c:if test="${notify.element == null}">
			notify('${notify.message}', options);
			</c:if>
		</c:forEach>
	});
	</script>
	</c:if>