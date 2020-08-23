package com.dev.springboot.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

/*
 * assertThat
 *  - assertj 테스트 검증 라이브러리의 검증 메소드
 */

public class HelloResponseDtoTest {

    @Test
    public void lombokTest() {
        //given
        String name = "test";
        int amount = 100;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}