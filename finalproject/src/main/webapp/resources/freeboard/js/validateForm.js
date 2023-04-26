function validateForm() {
    const title = document.getElementsByName("title")[0].value;
    
    if (title.trim() === "") {
      alert("제목을 입력해주세요.");
      document.getElementsByName("title")[0].focus();

      return false;
    }
  
    const content = document.getElementsByName("content")[0].value;

    if (content.trim() === "") {
      alert("내용을 입력해주세요.");
      document.getElementsByName("content")[0].focus();
      return false;
    }
    return true;
}

function confirmReset() {
  const result = confirm("모든 내용을 초기화 하시겠습니까?");

  return result;
}

function confirmGoToList() {
    const title = document.getElementsByName("title")[0].value;
    const content = document.getElementsByName("content")[0].value;

    if (title.trim() !== "" || content.trim() !== "") {
        const result = confirm("작성 중인 내용이 있습니다. 목록으로 이동하시겠습니까?");

        if (!result) {
            return false;
        }
    }
    return true;
}

function commentForm() {
  const content = document.getElementById("comment_content").value;

  if(content.trim() === ""){
    alert("댓글 내용을 입력해주세요.");
    document.getElementById("comment_content").focus();
    return false;
  }
  return true;
}