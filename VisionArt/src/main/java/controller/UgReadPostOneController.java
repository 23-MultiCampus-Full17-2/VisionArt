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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dto.CommentRq;
import dto.DeletePostRq;
import dto.LikeRq;
import dto.UgImageDTO;
import dto.UgPostDTO;
import dto.UgReadPostOneDTO;
import service.UgReadPostOneService;

@Controller
public class UgReadPostOneController {

	@Autowired
	UgReadPostOneService service;

	
	@GetMapping("/ug/{post_id}")
	@ResponseBody
	public ModelAndView readPostOne(@PathVariable int post_id) {
		UgReadPostOneDTO ugReadPostOne = service.getUgReadPostOne(post_id);
  		List<UgImageDTO> post_attachment = service.getImage(post_id);
  		int art_field_id = service.getArtFieldId(post_id); 
  		String art_field_name = service.getArtFieldName(art_field_id);
  		
  	    //작성자의 다른 게시글 중 조회수가 높은 5개 가져오기
  	    List<UgPostDTO> top5PopularPosts = service.getTop5PopularPosts(ugReadPostOne.getUgPostInfo().getMember_id());
  		
		ModelAndView mv = new ModelAndView();
		mv.addObject("ugReadPostOne", ugReadPostOne);
		//mv.addObject("art_field_id", art_field_id); // art_field_id를 뷰에 추가	
		mv.addObject("art_field_name", art_field_name); 
		mv.addObject("top5PopularPosts", top5PopularPosts); 
		if(!post_attachment.isEmpty()) {
			mv.addObject("post_attachment", post_attachment);
		}		
		
		mv.setViewName("UgReadPostOne");
		
		return mv;
	}
	
	@PostMapping("/ug/comment")
	@ResponseBody
	public CommentRq saveComment(@RequestBody CommentRq request) {
		// 임의의 member_id 설정
	    //int memberid = 1; // 예시로 임의의 숫자 1를 사용--나중에 삭제
	    
	    // request에서 member_id 값을 설정하거나 임의의 값으로 설정--나중에 삭제
	   // request.setMember_id(memberid);
	    
		System.out.println("request.getComment_content()"+request.getComment_content());
		service.insertComment(request.getComment_content(), request.getMember_id(), request.getPost_id());				
		
		return request;
	}
	
	
	@PostMapping("/ug/comment/delete")
	@ResponseBody
	public boolean deleteComment(int comment_id) {
		int res = service.deleteComment(comment_id);
		
		if(res > 0) {
			return true;
		} else {
			return false;
		}
	}
	

	@PostMapping("/ug/post/like")
	@ResponseBody
	public int clickLike(@RequestBody LikeRq request) {
		int isClick = service.selectLike(request.getPost_id(), request.getMember_id());
		
		if(isClick > 0) {
			service.deleteLike(request.getPost_id(), request.getMember_id());
		} else {
			service.insertLike(request.getPost_id(), request.getMember_id());
		}
				
		return service.selectLikeAll(request.getPost_id());
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
	
	
	@PostMapping("/ug/post/delete")
	@ResponseBody
	public int deletePost(@RequestBody DeletePostRq request) {
		return service.deletePost(request.getPost_id());
	}
	@PostMapping("/ug/increaseViews/{post_id}")
    @ResponseBody
    public ResponseEntity<String> increaseViews(@PathVariable int post_id) {
        try {
            // postId를 기반으로 해당 게시글의 조회수를 증가시키는 로직 추가
            service.increaseViews(post_id);

            // 이 예제에서는 단순히 성공 상태 메시지만 반환합니다.
            return ResponseEntity.ok("조회수 증가 성공");
        } catch (Exception e) {
            // 예외 발생 시 에러 상태 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("조회수 증가 실패");
        }
    }
}
