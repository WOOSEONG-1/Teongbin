package com.ssafy.teongbin.common.exception;

import lombok.Getter;

@Getter
public enum ErrorType {

    //----------------------- 유저 관련 부분 ------------------------------
    CONTENT_IS_NULL(400, "입력되지 않은 정보가 있습니다."),
    DUPLICATED_USERID(400, "중복된 아이디입니다."),
    FAILED_TO_ACQUIRE_LOCK(100, "락 권한을 얻는데 실패했습니다."),
    NOT_FOUND_PARK_TYPE(400, "유효하지 않은 값입니다."),
    NOT_FOUND_USER(401, "등록된 사용자가 없습니다."),
    NOT_MATCHING_INFO(401, "아이디 또는 비밀번호를 잘못 입력했습니다."),
    NOT_TOKEN(401, "토큰이 없습니다."),
    NOT_VALID_TOKEN(401, "토큰이 유효하지 않습니다."),
    NOT_FOUND_SANG(401, "김상훈 유저가 없습니다"),
    //-----------------------쓰레기통 관련 부분---------------------------
    NOT_FOUND_TRASHCAN(401, "등록된 쓰레기통이 없습니다"),
    //-----------------------숏컷 관련 부분---------------------------
    NOT_FOUND_SHORTCUT(401, "숏컷이 존재하지 않습니다."),
    USER_SHORTCUT_MISMATCH(401, "유저와 숏컷의 유저 정보가 일치하지 않습니다.");


    private int code;
    private String msg;

    ErrorType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}