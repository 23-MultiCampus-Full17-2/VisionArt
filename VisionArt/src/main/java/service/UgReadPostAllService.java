package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UgReadPostAllDAO;
import dto.UgArtFieldDTO;
import dto.UgImageDTO;
import dto.UgLikeDTO;
import dto.UgMemberDTO;
import dto.UgPostDTO;
import dto.UgReadPostAllDTO;

@Service
public class UgReadPostAllService {
	
	@Autowired
	UgReadPostAllDAO dao;
	
	public UgReadPostAllDTO selectUgReadPostAllByPostField(int page, int pageSize){
		int offset = page * pageSize;
		List<UgPostDTO> posts = dao.selectUgPostsByPostField(offset, pageSize);
		List<UgMemberDTO> members = dao.selectUgMembers();
		List<UgArtFieldDTO> arts = dao.getArtFieldName();
		return new UgReadPostAllDTO(posts, members, arts);
	}
	
	public int getTotalPosts() {
		return dao.getTotalPosts();
	}
	
	public List<UgLikeDTO> getLikes(int memberid){
		return dao.getLikes(memberid);
	}
	
	public void updateLike(int memberid, int post_id) {
		dao.updateLike(memberid, post_id);
	}
	
	public boolean likeExists(int memberid, int post_id) {
		  long count = dao.likeExists(memberid, post_id);
		  return count > 0;// count가 0보다 크면 true, 그렇지 않으면 false 반환
	}
	
	public void deleteLike(int memberid, int post_id) {
		dao.deleteLike(memberid, post_id);
	}
	
	public List<UgLikeDTO> likeTotal(){
		return dao.likeTotal();
	}
	
	public List<UgImageDTO> imageTotal(){
		return dao.imageTotal();
	}
	
	/* My Page      */
	public List<UgPostDTO> getMyPost(int memberid, int postNumber){
		return dao.getMyPost(memberid, postNumber);
	}
	
	public List<UgPostDTO> getMyAllPost(int member_id){
		return dao.getMyAllPost(member_id);
	}
	
	public List<UgMemberDTO> memberTotal(){
		return dao.selectUgMembers();
	}
	
	public int validatePw(int memberid, String password) {
		return dao.validatePw(memberid, password);
	}
	
	
}
