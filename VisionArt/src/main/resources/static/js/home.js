

document.addEventListener('DOMContentLoaded', function() {
	// post_id를 클릭한 postBox에서 가져와서 새로운 URL로 이동
	let postSubjects = document.querySelectorAll('.post-subject');
		let postSubjects1 = document.querySelectorAll('.post-subject1');
	let postImages = document.querySelectorAll('.post-img');
	let postImages1 = document.querySelectorAll('.post-img1');
	let likeImages = document.querySelectorAll('.like-img');
	let likeNums = document.querySelectorAll('.like-num');
	
	postSubjects.forEach(function(postSubject) {
		postSubject.addEventListener('click', function() {
					let post_id = postSubject.dataset.postId; 
					location.href = 'http://localhost:9071/ug/' + post_id;
			});
	});
		postSubjects1.forEach(function(postSubject1) {
		postSubject1.addEventListener('click', function() {
					let post_id = postSubject1.dataset.postId; 
					location.href = 'http://localhost:9071/post/' + post_id;
			});
	});
	
	postImages.forEach(function(postImage) {
			postImage.addEventListener('click', function() {
					let post_id = postImage.dataset.postId; 
					location.href = 'http://localhost:9071/ug/' + post_id;
			});
	});
	postImages1.forEach(function(postImage1) {
			postImage1.addEventListener('click', function() {
					let post_id = postImage1.dataset.postId; 
					location.href = 'http://localhost:9071/post/' + post_id;
			});
	});
	likeImages.forEach(function(likeImage) {
        likeImage.addEventListener('click', function() {
            let post_id = likeImage.dataset.postId; 
            let member_id = likeImage.dataset.memberId; 
            fetch('http://localhost:9071/post?post_id=' + post_id + '&member_id=' + member_id, {
           		method: 'GET',
	            headers: {
	                'Content-Type': 'application/json',
	                // You can add more headers if needed
	            },
        	})
        	.then(() => {
           		location.reload();
        	})
        });
        
    });
});