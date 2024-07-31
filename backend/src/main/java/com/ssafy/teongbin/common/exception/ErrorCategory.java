package com.ssafy.teongbin.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCategory {
    LOGIN_ERROR("로그인 에러"),
    TOKEN_ERROR("토큰 에러"),
    SIGNUP_ERROR("회원가입 에러");


    String category;

    ErrorCategory(String category) {
        this.category = category;
    }
}
