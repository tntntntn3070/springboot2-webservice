package com.dev.springboot.web;

import com.dev.springboot.service.posts.PostsService;
import com.dev.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
 * 머스테치 스타터를 사용할 경우
 *  - 문자열 반환 시, 앞의 경로와 뒤의 파일 확장자는 자동 지정
 *  - 경로 : src/main/resources/templates
 *  - 확장자 : .mustache
 *
 * Model
 *  - 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장
 *  - 여기서는 findAllDesc() 로 가져온 결과를 posts로 index.mustache 에 전달
 */
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
