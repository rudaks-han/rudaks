function notify(msg, options, element)
{
	var opts = {
		clickToHide : false,
		autoHide: true,
		autoHideDelay: 5000,
		globalPosition : 'top center',
		elementPosition : 'top right',
		className: 'error'
	};
	
	$.extend(opts, options);
		
	if (typeof element != 'undefined')
		$(element).notify(msg, opts);
	else
		$.notify(msg, opts);
}

var modal = {
	create : function()
	{
		backgroundOverlay(true);	
		
		//ajaxLoader(true);
		
		if ($('#modal').length == 0)
		{
			var html = '';
			html += '<div id="modal" class="modal">';
			html += '<div class="modal-dialog">';
			html += '  <div class="modal-content">';
			html += '    <div class="modal-header">';
			html += '      <button type="button" id="modalClose" class="close" data-dismiss="modal" aria-hidden="true">x</button>';
			html += '      <h4 class="modal-title" id="modalTitle">Modal Title</h4>';
			html += '    </div>';
			html += '    <div id="modalContents" class="modal-body">';
			html += '      <p>Modal Body</p>';
			html += '    </div>';
			html += '    <div class="modal-footer" id="modalButton">';
			//html += '      <button type="button" id="btnModalOk" class="btn btn-primary">OK</button>';
			html += '      <button type="button" id="btnModalClose" class="btn btn-default" data-dismiss="modal">Close</button>';
			html += '    </div>';
			html += '  </div>';
			html += '</div>';
			html += '</div>';
			
			$(html).appendTo('body');
		}
		
		var $modal = $('#modal');
		
		$modal.hide();
		if ($modal.length > 0)
		{
			var modalTop = 50;
			$modal.css({top : modalTop + 'px'});
			
			/*if (screen.height <= 800)
			{
				var modalTop = 50 + $(document).scrollTop();
				$modal.css({position:'absolute', top : modalTop + 'px'});
			}*/
			
			/*if (isMobileDevice())
			{
				var modalTop = 200;
				$modal.css({position:'absolute', top : modalTop + 'px'});
			}*/
						
			/*$('#modalClose').live('click touchend', function(e) {
				modal.hide();
			});*/
			
			$('#modalClose').on('click touchend', function(e) {
				modal.hide();
			});
			
			$('#btnModalClose').on('click', function() {
				modal.hide();
			});
		}
		
		$(document).on('keydown.backgroundOverlay', function(event) {
			event.preventDefault();
			if ($modal.length > 0 && event.keyCode && event.keyCode === jQuery.ui.keyCode.ESCAPE)
			{
				modal.hide();
			}
		});
	},
	show : function(data)
	{
		
		this.create();
	
		var $modal = $('#modal');
		
		if ($modal.length > 0 && typeof data != 'undefined')
		{			
			if ($('#notify_wrapper').is(':visible'))
				$('#notify_wrapper').remove();
			
			if (typeof data.contents == 'undefined' || data.contents == '')
				$('#modalContents').html('No Data');
			else
				$('#modalContents').html(data.contents);
			
			if (typeof data.title == 'undefined' || data.title == '')
				$('#modalTitle').html('Title');
			else
				$('#modalTitle').html(data.title);
			
			if (typeof data.btnOk != 'undefined' && data.btnOk != '')
			{
				$('#modalButton').append('<button type="button" id="btnModalOk" class="btn btn-primary">OK</button>');
			}
			
			//$(data.content).appendTo('#modalContents');
			//$(data.content).appendTo('#modalContents');
			$modal.fadeIn(100, function() {/*ajaxLoader(false);*/});
		}		
	},
	append : function(data)
	{		
		var $modal = $('#modal');
		if ($modal.length > 0 && typeof data != 'undefined')
		{
			if ($('#notify_wrapper').is(':visible'))
				$('#notify_wrapper').remove();
			
			$(data).appendTo('#modalContents');
			//$modal.show(0, function() {ajaxLoader(false);});
			$modal.fadeIn(100, function() {/*ajaxLoader(false)*/;});
			//$('#modal').show('drop', {}, 0, function() { ajaxLoader(false); });
		}
	},
	hide : function()
	{		
		backgroundOverlay(false);
		$('#modal').remove();
		//if (typeof event != 'undefined')
		//	event.preventDefault();
		$(document).off('keydown.backgroundOverlay');
		//ajaxLoader(false);
	}
};

function backgroundOverlay(flag, options)
{
	var overlayId = 'backgroundOverlay';
	var contentsId = 'body';
	var position = 'fixed';
	var opacity = '0.6';
	
	if (typeof options != 'undefined')
	{
		if (typeof options.overlayId != 'undefined')
			overlayId = options.overlayId;
		
		if (typeof options.contentsId != 'undefined')
			contentsId = options.contentsId;
		
		if (typeof options.position != 'undefined')
			position = options.position;
		
		if (typeof options.opacity != 'undefined')
			opacity = options.opacity; 
	}
	
	if (flag)
	{
		if ($("#" + overlayId).length == 0)
			$("<div id='" + overlayId + "' class='backgroundOverlay'></div>").appendTo(contentsId);
		
		var zIndex = 10000;
		/*$('div').each(function(i) {						
			if (parseInt($(this).css('z-index')) > zIndex)
				zIndex = $(this).css('zIndex');
		});*/
		
		$("#" + overlayId).css({
	        "opacity": "0.6",
	        position : position,
	        zIndex : zIndex,
	        opacity : opacity,
	        filter : 'alpha(opacity=' + opacity * 100+ ')'
	    });
				
	    $("#" + overlayId).show();
	}
	else
	{
		$("#" + overlayId).hide();
	}
}

function displayAttachList()
{
    var filePath = $('#postForm').find('#filePath').val();
    var fileName = $('#postForm').find('#fileName').val();
    var fileSize = $('#postForm').find('#fileSize').val();
    
    var arFilePath = filePath.split(",");
    var arFileName = fileName.split(",");
    var arFileSize = fileSize.split(",");
    
    $('.attach-result').html('');
    for (var i=0; i<arFilePath.length; i++)
    {
        $('.attach-result').append('<span class="filenameDisplay" style="margin:5px">' + arFileName[i] + ' <span class="glyphicon glyphicon-remove"></span></span>');
    }
    
    $('#postForm').find('.glyphicon-remove').on('click', function() {
        
        
        var filePath = $('#postForm').find('#filePath').val();
        var fileName = $('#postForm').find('#fileName').val();
        var fileSize = $('#postForm').find('#fileSize').val();
        
        var arFilePath = filePath.split(",");
        var arFileName = fileName.split(",");
        var arFileSize = fileSize.split(",");
        
        $('#postForm').find('#filePath').val('');
        $('#postForm').find('#fileName').val('');
        $('#postForm').find('#fileSize').val('');
        
        for (var i=0; i<arFilePath.length; i++)
        {
            if (i != $(this).parent('.filenameDisplay').index())
            {
                if ($('#postForm').find('#filePath').val() == '')
                {
                    $('#postForm').find('#filePath').val(arFilePath[i]);
                    $('#postForm').find('#fileName').val(arFileName[i]);
                    $('#postForm').find('#fileSize').val(arFileSize[i]);
                }
                else
                {
                    $('#postForm').find('#filePath').val($('#postForm').find('#filePath').val() + ',' + arFilePath[i]);
                    $('#postForm').find('#fileName').val($('#postForm').find('#fileName').val() + ',' + arFileName[i]);
                    $('#postForm').find('#fileSize').val($('#postForm').find('#fileSize').val() + ',' + arFileSize[i]);
                }
            }
        }
        
        $(this).parent('.filenameDisplay').remove();
    });
}