package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import dto.PostDTO2;

@Repository
@Mapper
public interface PostDAO {
//int registerPost(PostDTO2 postDTO);	
	int insertPost(PostDTO2 postDTO);

	// int saveImageFilename(int post_id, String originalFilename);
	int selectpost_id(PostDTO2 postDTO); //

	PostDTO2 getPostById(int post_id);

	List<String> getImageFilenames(int post_id);

	int updatePost(PostDTO2 postDTO);

	int updateImage(int post_id, String newFilename);

	int saveImageFilename1(int post_id, String newFilename);

	int deleteoldImages(int post_id);

	String getArtFieldName(int artFieldId);
	// int selectLatestpost_id(PostDTO2 postDTO);
	// void uploadImage(String imageName);

}
