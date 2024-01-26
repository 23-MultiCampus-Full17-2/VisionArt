// CreatePost.js



// 파일 선택 시 이벤트 핸들러
fileInput.addEventListener('change', function() {
    // 사용자가 선택한 파일들
    const files = fileInput.files;
    // 파일들의 개수 확인
    console.log('선택한 파일 개수:', files.length);
});


//작성버튼 클릭시 해당 게시글 상세페이지로 이동
//	function move() {
//		location.href = "ReadPostOne";
//	}
	
