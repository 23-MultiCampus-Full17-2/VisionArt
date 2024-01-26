package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import dto.CommentRq;
import dto.DeletePostRq;
import dto.ImageDTO;
import dto.LikeRq;
import dto.PostDTO;
import dto.ReadPostOneDTO;
import service.ReadPostOneService;

@Controller
public class ReadPostOneController {

	@Autowired
	ReadPostOneService service;
	
	@GetMapping("/post/{post_id}")
	@ResponseBody
	public ModelAndView readPostOne(@PathVariable int post_id) {
		ReadPostOneDTO readPostOne = service.getReadPostOne(post_id);
  		List<ImageDTO> post_attachment = service.getImage(post_id);
  		int art_field_id = service.getArtFieldId(post_id); //
  		String art_field_name = service.getArtFieldName(art_field_id);
  		
  		
		ModelAndView mv = new ModelAndView();
		mv.addObject("readPostOne", readPostOne);
		//mv.addObject("art_field_id", art_field_id); // art_field_id를 뷰에 추가	
		mv.addObject("art_field_name", art_field_name); 
				
		if(!post_attachment.isEmpty()) {
			mv.addObject("post_attachment", post_attachment);
		}		
	
		mv.setViewName("ReadPostOne");
		
		return mv;
	}
	
	
	@PostMapping("/comment")
	@ResponseBody
	public CommentRq saveComment(@RequestBody CommentRq request) {
		// 임의의 member_id 설정
	    //int memberid = 1; // 예시로 임의의 숫자 1를 사용--나중에 삭제
	    
	    // request에서 member_id 값을 설정하거나 임의의 값으로 설정--나중에 삭제
	   // request.setMember_id(memberid);
		  // 로그인 여부 확인
        if (request.getMember_id() == 0) {
            // 로그인이 안 된 경우
            throw new UnauthorizedException("로그인이 필요합니다.");
        }
	    
		System.out.println("request.getComment_content()"+request.getComment_content());
		service.insertComment(request.getComment_content(), request.getMember_id(), request.getPost_id());				
		
		return request;
	}
	
	
	@PostMapping("/comment/delete")
	@ResponseBody
	public boolean deleteComment(int comment_id) {
		int res = service.deleteComment(comment_id);
		
		if(res > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	@PostMapping("/post/like")
	@ResponseBody
	public int clickLike(@RequestBody LikeRq request) {
		 // 로그인 여부 확인
        if (request.getMember_id() == 0) {
            // 로그인이 안 된 경우
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

		int isClick = service.selectLike(request.getPost_id(), request.getMember_id());
		
		if(isClick > 0) {
			service.deleteLike(request.getPost_id(), request.getMember_id());
		} else {
			service.insertLike(request.getPost_id(), request.getMember_id());
		}
				
		return service.selectLikeAll(request.getPost_id());
	}
	// 예외 처리 클래스 추가
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public static class UnauthorizedException extends RuntimeException {
        public UnauthorizedException(String message) {
            super(message);
        }
    }
	//세션 완성되면 삭제후 위에 로직 사용하기 
//	@PostMapping("/vs/post/like")
//	@ResponseBody
//	public int clickLike(@RequestBody LikeRq request) {
//		System.out.println("request.getPost_id()"+request.getPost_id());
//	    System.out.println("request.getMember_id()"+request.getMember_id());
//	    int isClick = service.selectLike(request.getPost_id(), 1); // member_id를 1로 고정
//	    System.out.println("request.getPost_id()"+request.getPost_id());
//	    System.out.println("request.getMember_id()"+request.getMember_id());
//	    if (isClick > 0) {
//	        service.deleteLike(request.getPost_id(), 1); // member_id를 1로 고정
//	    } else {
//	        service.insertLike(request.getPost_id(), 1); // member_id를 1로 고정
//	    }
//
//	    return service.selectLikeAll(request.getPost_id());
//	}
	
	
	@PostMapping("/post/delete")
	@ResponseBody
	public int deletePost(@RequestBody DeletePostRq request) {
		return service.deletePost(request.getPost_id());
	}
	
	@PostMapping("/post/increaseViews/{post_id}")
    @ResponseBody
    public ResponseEntity<String> increaseViews(@PathVariable int post_id) {
        try {
            service.increaseViews(post_id);

            return ResponseEntity.ok("조회수 증가 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("조회수 증가 실패");
        }
    }

	@GetMapping("/post/otherPosts/{member_id}")
	@ResponseBody
	public List<PostDTO> getOtherPostsByAuthor(@PathVariable int member_id) {
	    List<PostDTO> otherPosts = service.getOtherPostsByAuthor(member_id);
	    return otherPosts;
	}
}
