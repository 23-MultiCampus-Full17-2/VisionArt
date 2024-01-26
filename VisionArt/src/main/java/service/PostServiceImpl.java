package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.PostDAO;
import dto.PostDTO2;


@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDAO postDAO;

//	@Override
//	public int registerPost(PostDTO2 postDTO) {
//		int post_id = postDAO.registerPost(postDTO);
//		return post_id;
//	}
	
	@Override
	public int insertPost(PostDTO2 postDTO) {
		int post_id = postDAO.insertPost(postDTO);
		return post_id;
	}


//	@Override
//	public void saveImageFilename(int post_id, String originalFilename) {
//		postDAO.saveImageFilename(post_id, originalFilename);
//	}
	

	
	@Override
	public void selectpost_id(PostDTO2 postDTO) {
		postDAO.selectpost_id(postDTO);
	}

	@Override
	public PostDTO2 getPostById(int post_id) {
		return postDAO.getPostById(post_id);
	}
	
	@Override
	public List<String> getImageFilenames(int post_id) {
	    List<String> filenames = postDAO.getImageFilenames(post_id);

	    if (filenames == null || filenames.isEmpty()) {
	        // 결과가 없는 경우
	        return new ArrayList<>(); // 빈 리스트 반환
	    } else {
	        // 결과가 있는 경우 리스트 반환
	        return filenames;
	    }
	}
	@Override
	public void updatePost(PostDTO2 postDTO) {
		postDAO.updatePost(postDTO);

	}

	
	@Override
	public int updateImage(int post_id, String newFilename) {
		return postDAO.updateImage( post_id, newFilename);
	}

	@Override
	public void saveImageFilename1(int post_id, String newFilename) {
		 postDAO.saveImageFilename1(post_id, newFilename);
		
	}

	@Override
	public int deleteoldImages(int post_id) {
		return postDAO.deleteoldImages(post_id); 
	}

	@Override
	public String getArtFieldName(int artFieldId) {
		return  postDAO.getArtFieldName(artFieldId);
	}


	
}
