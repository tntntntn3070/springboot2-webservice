package com.dev.springboot.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity
 *  - 테이블과 링크될 클래스임을 나타냄
 *  - 클래스의 카메케이스 이름을 언더스코어 네이밍으로 테이블 이름 매칭
 *
 * NoArgsConstructor
 *  - 기본 생성자 자동 추가
 *
 */
@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    /**
     * JPA 로 데이터베이스로 저장할 때 Enum 값을 어떤 형태로 저장할지 결정
     * 기본적으로는 int 로 된 숫자가 저장
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
