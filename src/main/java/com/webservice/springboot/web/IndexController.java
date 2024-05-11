package com.webservice.springboot.web;

import com.webservice.springboot.config.auth.LoginUser;
import com.webservice.springboot.config.auth.dto.SessionUser;
import com.webservice.springboot.service.posts.PostsService;
import com.webservice.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        
        // findAllDesc의 결과를 "posts"로 index.mustache에 전달
        model.addAttribute("posts", postsService.findAllDesc());

        // 파라미터 @LoginUser SessionUser user로 대체됨
        // SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        // 앞의 경로와 뒤의 파일 확장자는 자동으로 지정되어 View Resolver가 처리
        // src/main/resources/templates/ + index + .mustache
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}
