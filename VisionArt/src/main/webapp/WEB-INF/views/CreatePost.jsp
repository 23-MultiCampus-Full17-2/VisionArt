<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>VisionArt</title>
<script src="${path}/js/CreatePost.js"></script>
<script src="${path}/js/jquery-3.7.1.min.js"></script>
<link href="${path}/css/createpost.css" rel="stylesheet" type="text/css">

</head>
<body>
<%@ include file="header.jsp" %>
	<br><br><br>
	<section id="container">
		<div class="post">
			<form id="postForm" action="/post/write" method="post"
				enctype="multipart/form-data" accept-charset="utf-8">

				<div>
					<h2 id="posth2">게시글 작성</h2>
				</div>
				<div class="postInside">
				<!-- art_field_id를 저장할 hidden 필드 추가 -->
        <input type="hidden" id="artFieldId" name="art_field_id" value="${artFieldId}">
                    
              <div>
			    <p>종류</p>
			   <div class="box">
			    <select name="selectedArtField" id="selectedArtField" onchange="setArtFieldId()">
			      
			        <option value="유머글" ${artFieldId == 10 ? 'selected' : ''}>유머글</option>
			        <option value="자랑하기" ${artFieldId == 11 ? 'selected' : ''}>자랑하기</option>
			        <option value="질문하기" ${artFieldId == 12 ? 'selected' : ''}>질문하기</option>
			        <option value="정보공유" ${artFieldId == 13 ? 'selected' : ''}>정보공유</option>
			        <option value="그냥" ${artFieldId == 14 ? 'selected' : ''}>그냥</option>
			         <option value="그냥" ${artFieldId == 15 ? 'selected' : 'selected'}>카테고리선택하기</option>
			    </select>
			</div>
				 </div>
					<p>제목</p>
					<input type="text" id="postTitle" name="title" required>
					<p>내용</p>
					<textarea id="postContent" name="content" required></textarea>
					 <div class="box">
					<input type="file" name="imageFiles" accept="image/*" value="파일첨부" multiple>
					<input type="file" name="imageFiles" accept="image/*" value="파일첨부" multiple>
		

					</div>
				<br> <br>
					<div>
						<input type="submit" value="작성">
					</div>
				</div>
			</form>
		</div>
	</section>


<%@ include file="footer.jsp" %>
</body>
</html>
