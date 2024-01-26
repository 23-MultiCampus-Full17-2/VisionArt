package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.UgReadPostOneDAO;
import dto.PostDTO;
import dto.UgCommentDTO;
import dto.UgImageDTO;
import dto.UgPostDTO;
import dto.UgReadPostOneDTO;

@Service
public class UgReadPostOneService {
	
	@Autowired
	UgReadPostOneDAO dao;
		
	
	public UgReadPostOneDTO getUgReadPostOne(int post_id) {
		UgPostDTO ugPostDetail = dao.selectPostDetail(post_id);		
		String ugMemberName = dao.selectMemberName(ugPostDetail.getMember_id());
		int ugLikeCnt = dao.selectLikeAll(post_id);
		List<UgCommentDTO> ugComments = dao.selectComment(post_id);		
		
		return new UgReadPostOneDTO(ugMemberName, ugPostDetail, ugLikeCnt, ugComments);
	}
	
	
	public List<UgImageDTO> getImage(int post_id){
		return dao.getImage(post_id);
	}
	
	public int insertComment(String comment_content, int member_id, int post_id) {
		return dao.insertComment(comment_content, member_id, post_id);
	}
	
	public int deleteComment(int comment_id) {
		return dao.deleteComment(comment_id);
	}
	
	
	public int selectLike(int post_id, int member_id) {
		return dao.selectLike(post_id, member_id);
	}
	
	
	public int deleteLike(int post_id, int member_id) {
		return dao.deleteLike(post_id, member_id);
	}
	
	public int insertLike(int post_id, int member_id) {
		return dao.insertLike(post_id, member_id);
	}
	
	public int selectLikeAll(int post_id) {
		return dao.selectLikeAll(post_id);
	}
	
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
		UgPostDTO ugPost = dao.selectPostDetail(post_id);
		ugPost.setViews(ugPost.getViews() + 1);
        dao.updatePost(ugPost);
		
	}
	public List<UgPostDTO> getTop5PopularPosts(int member_id) {
	    return dao.getTop5PopularPosts(member_id);
	}
}
