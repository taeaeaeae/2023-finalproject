$(document).on('click', '.commentModifyLink', function(e) {
  e.preventDefault();

  var fbcid = $(this).data('fbcid');
  var uids = $(this).data('uids');
  var content = $(this).data('content');
  var form = $('.commentModifyForm');

  $('#comment-content', form).val(content);
  $('#comment_fbcid', form).val(fbcid); 
  $('#comment_uids', form).val(uids);
  form.show();
});

$(document).on('click', '.commentModifyBtn', function() {
  var form = $(this).closest('.commentModifyForm');
  var fbcid = $('#comment_fbcid', form).val();
  var uids = $('#comment_uids', form).val();
  var content = $('#comment-content', form).val();

  $.ajax({
    type: 'POST',
    url: '/comment/modify',
    data: {
      fbcid: fbcid,
      uids: uids,
      content: content
    },
    success: function(data) {
      if(data.success){
        console.log(data);
        // 수정된 comment 받기
        var modifiedComment = data.FreeBoardCommentVO;
        // 해당 comment의 내용을 변경합니다.
        $(".commentList li[data-fbcid='" + modifiedComment.fbcid + "'].commentContent").html(modifiedComment.content);
		$('#comment-content').focus();
     }
    },
    error: function(xhr, status, error) {
      console.log("오류 발생")
    }
  });
});
