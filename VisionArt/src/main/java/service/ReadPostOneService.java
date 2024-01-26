package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.ReadPostOneDAO;
import dto.CommentDTO;
import dto.ImageDTO;
import dto.PostDTO;
import dto.ReadPostOneDTO;

@Service
public class ReadPostOneService {
	
	@Autowired
	ReadPostOneDAO dao;
		
	/**
	 * 게시물 상세 조회
	 * @param post_id 게시글 식별번호
	 * @return 게시글 상세 정보
	 */
	public ReadPostOneDTO getReadPostOne(int post_id) {
		PostDTO postDetail = dao.selectPostDetail(post_id);		
		String memberName = dao.selectMemberName(postDetail.getMember_id());
		int likeCnt = dao.selectLikeAll(post_id);
		List<CommentDTO> comments = dao.selectComment(post_id);		
		
		return new ReadPostOneDTO(memberName, postDetail, likeCnt, comments);
	}
	
	/**
	 * 이미지 조회
	 * @param post_id
	 * @return 이미지 리스트
	 */
	public List<ImageDTO> getImage(int post_id){
		return dao.getImage(post_id);
	}
	
	/**
	 * DB에 댓글 저장
	 * @param content
	 * @param member_id
	 * @param post_id
	 * @return 추가 행 개수
	 */
	public int insertComment(String comment_content, int member_id, int post_id) {
		return dao.insertComment(comment_content, member_id, post_id);
	}
	
	/**
	 * DB에서 댓글 삭제
	 * @param post_id
	 * @return 삭제 행 개수
	 */
	public int deleteComment(int comment_id) {
		return dao.deleteComment(comment_id);
	}
	
	/**
	 * 좋아요 조회
	 * @param post_id
	 * @param member_id
	 * @return
	 */
	public int selectLike(int post_id, int member_id) {
		return dao.selectLike(post_id, member_id);
	}
	
	/**
	 * 좋아요 삭제
	 * @param post_id
	 * @param member_id
	 * @return
	 */
	public int deleteLike(int post_id, int member_id) {
		return dao.deleteLike(post_id, member_id);
	}
	
	/**
	 * 좋아요 추가
	 * @param post_id
	 * @param member_id
	 * @return
	 */
	public int insertLike(int post_id, int member_id) {
		return dao.insertLike(post_id, member_id);
	}
	
	/**
	 * 게시물 좋아요 개수 조회
	 * @param post_id
	 * @return
	 */
	public int selectLikeAll(int post_id) {
		return dao.selectLikeAll(post_id);
	}
	
	/**
	 * 게시물 삭제
	 * @param post_id
	 * @return
	 */
	public int deletePost(int post_id) {
		return dao.deletePost(post_id);
	}
	public int getArtFieldId(int post_id) {
	    return dao.getArtFieldId(post_id);
	}

	public String getArtFieldName(int art_field_id) {
		return  dao.getArtFieldName(art_field_id);
	}
	@Transactional
	public void increaseViews(int post_id) {
		PostDTO post = dao.selectPostDetail(post_id);
        post.setViews(post.getViews() + 1);
        dao.updatePost(post);
		
	}

	public List<PostDTO> getOtherPostsByAuthor(int member_id) {
		 return dao.getOtherPostsByAuthor(member_id);
	}
}
