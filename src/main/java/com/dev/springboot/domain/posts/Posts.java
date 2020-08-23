package com.dev.springboot.domain.posts;

import com.dev.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
 * Entity
 *  - 테이블과 링크될 클래스임을 나타냄
 *  - 클래스의 카메케이스 이름을 언더스코어 네이밍으로 테이블 이름 매칭
 *
 * NoArgsConstructor
 *  - 기본 생성자 자동 추가
 *
 * GeneratedValue
 *  - PK 의 생성 규칙
 *  - auto_increment 적용
 *
 * Column
 *  - 테이블의 컬럼을 나타내며 생략해도 해당 클래스의 필드는 모두 컬럼으로 인식
 *  - 사용 이유 : 기본값 외 추가 변경 옵션이 있을 겨우 (컬럼 사이즈, 타입 변경 등)
 *
 * Builder
 *  - 빌더 패턴 클래스 생성
 *  - 생성자 상단에 선언 시 생성자에 포함된 필드만 필더에 포함
 */
@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
