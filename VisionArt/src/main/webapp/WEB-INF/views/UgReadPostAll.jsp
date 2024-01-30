<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>VisionArt</title>
<link rel="stylesheet" href="${path}/css/UgReadPostAll.css">
</head>
<body>
<%@ include file="header.jsp" %>
	<section class="home">
	<button type="button" class="btn1" onclick="location.href = '/ug/write'">작품 전시</button>
			<br>
	<h1>유저갤러리</h1>
		<section class="post">
<!--게시물 목록 섹션:
    ${all.posts}에서 받아온 게시물 목록을 순회하며 각 게시물을 동적으로 생성
    게시물 이미지, 제목, 작성자 정보, 좋아요 정보 등을 표시  -->
			<c:forEach var="post" items="${all.posts}">
				<div class="post-box">
					<div class="art-field">
				<p class="art-field-name" data-post-id="${post.post_id}">
				<c:forEach var="arts" items="${all.arts}">
				<c:if test="${arts.art_field_id eq post.art_field_id}">
				${arts.art_field_name}
				</c:if>
				</c:forEach>
				</p>
				<%-- <div class="art-field-id" data-post-id="${post.post_id}">${post.art_field_id}</div> --%>
			</div>
			
					<c:forEach var="post_attachment" items="${images}">
						<c:if test="${post_attachment.post_id == post.post_id}">
							<c:set var="post_att_path" value="${post_attachment.post_att_path}" />
						</c:if>
					</c:forEach>
					
					<img src="/visionart/${post_att_path}" class="post-img" data-post-id="${post.post_id}">
					
					<h2 class="post-title" data-post-id="${post.post_id}">${post.title}</h2>
					
				
					
					<div class="profile">
						<img src="${path}/icon/user.png" class="profile-img">
						<p class="profile-name">
							<c:forEach var="member" items="${all.members}">
								<c:if test="${post.member_id eq member.member_id}">
            						${member.name}
        						</c:if>
							</c:forEach>
						</p>

					</div>
				
					
					<div class="viewCount" data-post-id="${post.post_id}"> <p>조회수 </p> ${post.views}</div>
	<!--좋아요 이미지 및 개수: 각 게시물에 대한 좋아요 이미지와 좋아요 개수를 표시
    좋아요 개수는 ${likeNum}에 표시되며, 이미지는 좋아요 여부에 따라 변경됨  -->
					<div class="like">
						<c:set var="likeImage" value="${path}/icon/heart.png" />

						<c:forEach var="personal" items="${likes}">
							<c:if test="${post.post_id == personal.post_id}">
								<c:set var="likeImage" value="${path}/icon/redheart.png" />
							</c:if>
						</c:forEach>

						<img src="${likeImage}" class="like-img"
							data-member-id="${session.getAttribute('memberid')}"
							data-post-id="${post.post_id}">

						<p class="like-num">
							<c:set var="likeNum" value="0" />
							<c:forEach var="like" items="${likeTotal}">
								<c:if test="${like.post_id == post.post_id}">
									<c:set var="likeNum" value="${likeNum + 1}" />
								</c:if>
							</c:forEach>
							${likeNum}
						</p>
					</div>
				</div>
			</c:forEach>

		</section>
	</section>
<!-- 페이지네이션 섹션:${currentPage}, ${totalPages} 등을 사용하여 페이지네이션을 생성함
    현재 페이지는 "active" 클래스를 가지며, 페이지를 클릭하면 해당 페이지로 이동하도록 링크가 설정됨 -->
	<section class="pagination-section">
		<ul class="pagination">
			<c:if test="${currentPage > 0}">
				<li><a href="?page=${currentPage - 1}">이전</a></li>
			</c:if>

			<c:forEach begin="0" end="${totalPages - 1}" varStatus="loop">
				<c:choose>
					<c:when test="${currentPage eq loop.index}">
						<li class="active"><span>${loop.index + 1}</span></li>
					</c:when>
					<c:otherwise>
						<li><a href="?page=${loop.index}">${loop.index + 1}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:if test="${currentPage < totalPages - 1}">
				<li><a href="?page=${currentPage + 1}">다음</a></li>
			</c:if>
		</ul>
	</section>
<%-- 	<script src="${path}/static/js/ReadPostAll.js"></script> --%>
<script>
document.addEventListener('DOMContentLoaded', function() {
    // post_id를 클릭한 postBox에서 가져와서 새로운 URL로 이동
    let postTitles = document.querySelectorAll('.post-title');
    let postImages = document.querySelectorAll('.post-img');
    let likeImages = document.querySelectorAll('.like-img');
    
    postTitles.forEach(function(postTitle) {
        postTitle.addEventListener('click', function() {
            let post_id = postTitle.dataset.postId; 
            location.href = 'http://localhost:9071/ug/' + post_id; //To Read Post One
        });
    });
    
    postImages.forEach(function(postImage) {
        postImage.addEventListener('click', function() {
            let post_id = postImage.dataset.postId; 
            location.href = 'http://localhost:9071/ug/' + post_id; //To Read Post One
        });
    });
    
    likeImages.forEach(function(likeImage) {
        likeImage.addEventListener('click', function() {
            let post_id = likeImage.dataset.postId;
            let member_id = likeImage.dataset.memberId; 
          console.log("post_id:", post_id, "member_id:", member_id); //값 확인
   
			// 값이 정의되어 있고 숫자로 변환 가능할 때에만 변환
			if (post_id && !isNaN(post_id)) {
			    post_id = parseInt(post_id);
			} else {
			    post_id = 0; // 값이 정의되어 있지 않거나 변환 불가능한 경우 기본값 0으로 설정
			}
			
			if (member_id && !isNaN(member_id)) {
			    member_id = parseInt(member_id);
			} else {
			    member_id = 0; // 값이 정의되어 있지 않거나 변환 불가능한 경우 기본값 0으로 설정
			}
			 // 값이 안전하게 변환된 후에 fetch 요청 보내기
            fetch('http://localhost:9071/ug?post_id=' + post_id + '&member_id=' + member_id, {
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

</script>
<%@ include file="footer.jsp" %>
</body>
</html>