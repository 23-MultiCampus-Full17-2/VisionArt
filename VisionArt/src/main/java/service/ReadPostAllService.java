package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ReadPostAllDAO;
import dto.ArtFieldDTO;
import dto.ImageDTO;
import dto.InformDTO;
import dto.LikeDTO;
import dto.MemberDTO;
import dto.NoticeDTO;
import dto.PostDTO;
import dto.ReadPostAllDTO;

@Service
public class ReadPostAllService {
	
	@Autowired
	ReadPostAllDAO dao;
	
	public ReadPostAllDTO getReadPostAll(int page, int pageSize){
		int offset = page * pageSize;
		List<PostDTO> posts = dao.selectPosts(offset, pageSize);
		List<MemberDTO> members = dao.selectMembers();
		List<ArtFieldDTO> arts = dao.getArtFieldName();
		
		return new ReadPostAllDTO(posts, members, arts);
	}
	
	public int getTotalPosts() {
		return dao.getTotalPosts();
	}
	
	public List<LikeDTO> getLikes(int memberid){
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
	
	public List<LikeDTO> likeTotal(){
		return dao.likeTotal();
	}
	
	public List<ImageDTO> imageTotal(){
		return dao.imageTotal();
	}
	
	/* My Page      */
	public List<PostDTO> getMyPost(int memberid, int postNumber){
		return dao.getMyPost(memberid, postNumber);
	}
	
	public List<PostDTO> getMyAllPost(int member_id){
		return dao.getMyAllPost(member_id);
	}
	
	public List<MemberDTO> memberTotal(){
		return dao.selectMembers();
	}
	
	public int validatePw(int memberid, String password) {
		return dao.validatePw(memberid, password);
	}
	
	public void deleteMember(int member_id) {
		dao.deleteMember(member_id);
	}
	/* My Page      */
	
	public List<InformDTO> getNoticeAll() {
		return dao.selectAllNotice();
	}
	
	public NoticeDTO getAllNoitce() {
		List<InformDTO> informs = dao.selectAllNotice();
		List<MemberDTO> members = dao.selectMembers();
		return new NoticeDTO(members, informs);
	}
	
	public void updateNotice(String title, String textbox) {
		dao.updateNotice(title, textbox);
	}
	
	public void deleteNotice(int informid) {
		dao.deleteNotice(informid);
	}
	
	/* 추가된 내용(notice) */
	public boolean checkAuthority(int member_id) {
		return dao.selectAuthority(member_id);
	}


}
