package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserGalleryDAO;
import dto.UgPostDTO2;

@Service
public class UserGalleryService {

	@Autowired
	UserGalleryDAO ugDAO;
//	@Override
//	public int registerPost(UgPostDTO2 ugPostDTO) {
//		int post_id = ugDAO.registerPost(ugPostDTO);
//		return post_id;
//	}
	
	public int insertPost(UgPostDTO2 ugPostDTO) {
		int post_id = ugDAO.insertPost(ugPostDTO);
		return post_id;
	}



//	public void saveImageFilename(int post_id, String originalFilename) {
//		ugDAO.saveImageFilename(post_id, originalFilename);
//	}
	

	
	public void selectpost_id(UgPostDTO2 ugPostDTO) {
		ugDAO.selectpost_id(ugPostDTO);
	}

	
	public UgPostDTO2 getPostById(int post_id) {
		return ugDAO.getPostById(post_id);
	}
	
	
	public List<String> getImageFilenames(int post_id) {
	    List<String> filenames = ugDAO.getImageFilenames(post_id);

	    if (filenames == null || filenames.isEmpty()) {
	        // 결과가 없는 경우
	        return new ArrayList<>(); // 빈 리스트 반환
	    } else {
	        // 결과가 있는 경우 리스트 반환
	        return filenames;
	    }
	}
	
	public void updatePost(UgPostDTO2 ugPostDTO) {
		ugDAO.updatePost(ugPostDTO);

	}

	
	
	public int updateImage(int post_id, String newFilename) {
		return ugDAO.updateImage( post_id, newFilename);
	}

	
	public void saveImageFilename1(int post_id, String newFilename) {
		 ugDAO.saveImageFilename1(post_id, newFilename);
		
	}

	
	public int deleteoldImages(int post_id) {
		return ugDAO.deleteoldImages(post_id); 
	}

	public String getArtFieldName(int artFieldId) {
		return  ugDAO.getArtFieldName(artFieldId);
	}

}
