package com.dev.springboot.web.dto;

import com.dev.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * Getter
 *  - 클래스 내 모든 필드의 Getter 메소드 자동생성
 *
 * NoArgsConstructor
 *  - 기본 생성자 자동 추가
 *
 * Builder
 *  - 해당 클래스의 빌더 패턴 클래스 생성
 *  - 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
 */
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
