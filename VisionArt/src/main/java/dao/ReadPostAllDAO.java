package dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import dto.ArtFieldDTO;
import dto.ImageDTO;
import dto.InformDTO;
import dto.LikeDTO;
import dto.MemberDTO;
import dto.PostDTO;

@Repository
@Mapper
public interface ReadPostAllDAO {

	public List<PostDTO> selectPosts(int offset, int pageSize);

	public List<MemberDTO> selectMembers();
	
	public List<ArtFieldDTO> getArtFieldName();

	public int getTotalPosts();

	public List<LikeDTO> getLikes(int memberid);


	public void updateLike(int memberid, int post_id);


	public long likeExists(int memberid, int post_id);

	public void deleteLike(int memberid, int post_id);


	public List<LikeDTO> likeTotal() ;
	

	public List<ImageDTO> imageTotal();
	



	public List<PostDTO> getMyPost(int memberid, int postNumber);
	


	public List<PostDTO> getMyAllPost(int memberid);
	

	public int validatePw(int memberid, String password);
	


	public void deleteMember(int memberid);
	

	
	public List<InformDTO> selectAllNotice();


	public void updateNotice(String title, String textbox);

	
	public void deleteNotice(int informid);
	

	
	/* 추가된 내용(notice) */
	public boolean selectAuthority(int member_id);

	
	

}
