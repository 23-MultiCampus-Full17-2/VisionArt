package controller;

import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import dto.ImageDTO1;
import dto.PostDTO2;
import jakarta.servlet.http.HttpSession;
import service.PostService;


@Controller
@RequestMapping("/post")
public class PostController {
	String originalFilename;

		@Autowired
		@Qualifier("postServiceImpl")
		private PostService postService;

		@GetMapping("/write")
		public String showWritePage() {
		return "CreatePost";
		}

		@PostMapping("/write")
		public String writeProcess(HttpSession session,
				@RequestParam(name = "art_field_id") int artFieldId,
				PostDTO2 postDTO, ImageDTO1 imageDTO) throws Exception {

			// 임의의 값으로 member_id 설정 (예: 1)
		   // int member_id = 1; // 이 값을 원하는 값으로 변경
		  //  session.setAttribute("member_id", member_id);
		   // System.out.println(member_id);
		    
		 // postDTO에 member_id 설정
		   // postDTO.setMember_id(member_id); // 이는 postDTO에 member_id를 설정하는 가정하에 작성된 코드.
		    //실제로는 postDTO 객체에 member_id를 설정할 수 있는 방법을 확인하고 적용함
		  //로그인, 세션기능이 완성되면 위에(임의의값부터 ) 지우고 이코드로 대체
			int memberid = (int) session.getAttribute("memberid");
			 postDTO.setMember_id(memberid);
		    // post_field_id 설정
		    postDTO.setPost_field_id(0); // post_field_id를 0으로 설정
		 // art_field_id 설정	 
			 postDTO.setArt_field_id(artFieldId);
		    
			// Save post details
		     postService.insertPost(postDTO);
			 int post_id = postDTO.getPost_id();
			 
			// Save images
			 String savePath = "c:/visionart/";

			 //MultipartFile imageFile = imageDTO.getImageFile();
			 //originalFilename = imageFile.getOriginalFilename();
			  // 여러 파일 받기
			 List<MultipartFile> imageFiles = imageDTO.getImageFiles();
			    if (imageFiles != null && !imageFiles.isEmpty()) {
			        for (MultipartFile imageFile : imageFiles) {
			           // String originalFilename = imageFile.getOriginalFilename();
			            // 파일 저장 및 처리
			            if (!imageFile.isEmpty()) {
			                String filename = imageFile.getOriginalFilename();
			                File saveFile = new File(savePath + filename);
			                int counter = 1;
			                String newFilename = filename;
			                
			                // Check if the file with the same name already exists
			                while (saveFile.exists()) {
			                    String beforeExt = filename.substring(0, filename.lastIndexOf("."));
			                    String ext = filename.substring(filename.lastIndexOf("."));
			                    newFilename = beforeExt + "(" + counter + ")" + ext;
			                    saveFile = new File(savePath + newFilename);
			                    counter++;
			                }

			                imageFile.transferTo(saveFile);
			                post_id =postDTO.getPost_id();
			                postService.saveImageFilename1(post_id, newFilename);
			            }
			        }
			    }
			    
			    return "redirect:/post/" + post_id;
			}

		@GetMapping("/edit/{post_id}")
		public String showEditPage(@PathVariable int post_id, Model model) {
			
			
			PostDTO2 postDTO = postService.getPostById(post_id);
			List<String>  imageFilenames = postService.getImageFilenames(post_id); // 이미지 파일 이름 가져오기

			String title = postDTO.getTitle(); // 게시글 제목 가져오기
			String content = postDTO.getContent(); // 게시글 내용 가져오기
			int art_field_id = postDTO.getArt_field_id();
			
			model.addAttribute("detaildto", postDTO);
			System.out.println("수정창: 게시글 가져오기 중 postDTO" + postDTO);

			model.addAttribute("imageFilenames", imageFilenames);
			System.out.println("수정창: 게시글 가져오기 중 imageFilenames" + imageFilenames);

			model.addAttribute("title", title); // 모델에 게시글 제목 추가
			model.addAttribute("content", content); // 모델에 게시글 내용 추가
			model.addAttribute("artFieldId", art_field_id); // art_field_id 모델에 추가
			
			return "UpdatePost";
		}

		@PostMapping("/edit/{post_id}")
		public String editPost(@PathVariable int post_id, 
				@RequestParam(name = "art_field_id") int artFieldId,
		        PostDTO2 postDTO, ImageDTO1 imageDTO) throws Exception {
			 // post_field_id 설정
		    postDTO.setPost_field_id(0); // 여기서 post_field_id를 0으로 설정
		 // 요청 매개변수에서 art_field_id를 가져와서 설정
		    postDTO.setArt_field_id(artFieldId);
		    
		    postService.updatePost(postDTO);
		   

		    // Save images
		    String savePath = "c:/visionart/";
		    String filename = null, newFilename = null;

		    // 기존 이미지 삭제
		    postService.deleteoldImages(postDTO.getPost_id());

		    List<MultipartFile> imageFiles = imageDTO.getImageFiles();

		    if (imageFiles != null && !imageFiles.isEmpty()) {
		        for (MultipartFile imageFile : imageFiles) {
		            //String originalFilename = imageFile.getOriginalFilename();
		            
		            if (!imageFile.isEmpty()) {
		                 filename = imageFile.getOriginalFilename();
		                File saveFile = new File(savePath + filename);
		                int counter = 1;
		                newFilename = filename;

		                // 기존파일 여부 및 숫자추가
		                while (saveFile.exists()) {
		                    String beforeExt = filename.substring(0, filename.lastIndexOf("."));
		                    String ext = filename.substring(filename.lastIndexOf("."));
		                    newFilename = beforeExt + "(" + counter + ")" + ext;
		                    saveFile = new File(savePath + newFilename);
		                    counter++;
		                }

		                imageFile.transferTo(saveFile);
		                postService.saveImageFilename1(post_id, newFilename);
		            }
		        }
		    }

		    return "redirect:/post/" + post_id;  // 해당 게시글 상세창으로 이동
		}
}