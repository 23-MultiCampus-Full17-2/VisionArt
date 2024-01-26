package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import dto.UgPostDTO2;

@Repository
@Mapper
public interface UserGalleryDAO {
	// int registerPost(UgPostDTO2 ugPostDTO);
	int insertPost(UgPostDTO2 ugPostDTO);

	// int saveImageFilename(int post_id, String originalFilename);
	int selectpost_id(UgPostDTO2 ugPostDTO); //

	UgPostDTO2 getPostById(int post_id);

	List<String> getImageFilenames(int post_id);

	int updatePost(UgPostDTO2 ugPostDTO);

	int updateImage(int post_id, String newFilename);

	int saveImageFilename1(int post_id, String newFilename);

	int deleteoldImages(int post_id);
	
	public String getArtFieldName(int artFieldId);

	// int selectLatestpost_id(UgPostDTO2 ugPostDTO);
	// void uploadImage(String imageName);

}
