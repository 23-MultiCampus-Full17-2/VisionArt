package service;

import java.util.List;

import org.springframework.stereotype.Service;

import dto.PostDTO2;

@Service
public interface PostService {
//int registerPost(PostDTO2 postDTO);
	int insertPost(PostDTO2 postDTO);

	//void saveImageFilename(int post_id, String originalFilename);
	void selectpost_id(PostDTO2 postDTO); //

	PostDTO2 getPostById(int post_id);
	List<String> getImageFilenames(int post_id);
	void updatePost(PostDTO2 postDTO);
	int updateImage(int post_id,String newFilename);
	void saveImageFilename1(int post_id, String newFilename);

	int deleteoldImages(int post_id);
	String getArtFieldName(int artFieldId);
	
}

