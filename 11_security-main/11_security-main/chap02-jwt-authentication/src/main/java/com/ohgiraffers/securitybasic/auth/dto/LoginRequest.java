package com.ohgiraffers.securitybasic.auth.dto;

import jakarta.validation.constraints.NotBlank;

/*
* 로그인 요청의 아이디와 비밀번호를 함께 받는 DTO
* record는 값을 읽는 userId(), password() 등을 만들어주며 setter는 제공하지 않음
* */
public record LoginRequest (

    @NotBlank
    String userId,

    @NotBlank
    String password

) {}


