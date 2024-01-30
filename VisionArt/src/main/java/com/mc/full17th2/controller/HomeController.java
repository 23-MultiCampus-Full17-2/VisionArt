package com.mc.full17th2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mc.full17th2.dto.HitHomeDTO;
import com.mc.full17th2.dto.HomeDTO;
import com.mc.full17th2.dto.HomeForGalleryDTO;
import com.mc.full17th2.dto.ImageDTO;
import com.mc.full17th2.dto.LikeDTO;
import com.mc.full17th2.dto.ReadSearchAllAuthorDTO;
import com.mc.full17th2.dto.ReadSearchAllCategoryDTO;
import com.mc.full17th2.dto.ReadSearchAllDTO;
import com.mc.full17th2.dto.ReadSearchAllFgDTO;
import com.mc.full17th2.dto.ReadSearchAllUgDTO;
import com.mc.full17th2.service.HomeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    HomeService service;

    @GetMapping("/")
    public String home(Model model, @RequestParam(defaultValue = "0") int page, HttpSession session,
            @RequestParam(required = false) Integer post_id, @RequestParam(required = false) Integer member_id) {

        Integer memberIdObj = (Integer) session.getAttribute("memberid");
        int memberid = (memberIdObj != null) ? memberIdObj : 0;

        // 좋아요 버튼 동작 부분
        if (memberid != 0 && post_id != null && member_id != null) {
            boolean result = service.likeExists(member_id, post_id);

            if (result) {
                service.deleteLike(member_id, post_id);
            } else {
                service.updateLike(member_id, post_id);
            }
        }

        List<LikeDTO> likes = service.getLikes(memberid);
        List<LikeDTO> likeTotal = service.likeTotal();
        List<ImageDTO> images = service.imageTotal();
        int pageSize = 5;
        int totalPosts = service.getTotalPosts();

        HitHomeDTO hitHome = service.getHitHome(page, pageSize);

        List<Integer> id = service.getid();
        HomeDTO pophome = service.getPopPosts(id);
        pageSize = 10;
        HomeForGalleryDTO home = service.getHome(page, pageSize); // only art

        model.addAttribute("images", images); // jsp - item
        model.addAttribute("likes", likes);
        model.addAttribute("likeTotal", likeTotal);
        model.addAttribute("hitHome", hitHome);
        model.addAttribute("home", home);
        model.addAttribute("pophome", pophome);
        model.addAttribute("session", session);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", Math.ceil(totalPosts / (double) pageSize));

        return "home";
    }

	

}
