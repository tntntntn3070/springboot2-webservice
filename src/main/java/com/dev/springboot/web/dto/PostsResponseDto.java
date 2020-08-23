package com.dev.springboot.web.dto;

import com.dev.springboot.domain.posts.Posts;
import lombok.Getter;

/*
 * Getter
 *  - 클래스 내 모든 필드의 Getter 메소드 자동생성
 */
@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
