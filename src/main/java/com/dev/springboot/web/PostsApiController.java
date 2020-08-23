package com.dev.springboot.web;

import com.dev.springboot.service.posts.PostsService;
import com.dev.springboot.web.dto.PostsResponseDto;
import com.dev.springboot.web.dto.PostsSaveRequestDto;
import com.dev.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

/*
 * RequiredArgsConstructor
 *  - final 이 선언된 모든 필드를 인자값으로 하는 생성자를 생성 (롬복)
 *  - Autowired 로 Bean 를 주입하지 않아도 됨
 *
 *  RestController
 *   - JSON 을 반환하는 컨트롤러로 정의
 *   - 기존 메소드별 선언했던 ResponseBody 대체
 *
 *  PostMapping, PutMapping, GetMapping
 *   - HTTP Method 인 Post, Put, Get 의 요청을 받을 수 있는 API
 *
 *  RequestBody
 *   - JSON 정보를 객체로 변환
 *
 *  PathVariable
 *   -
 */
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }
}
