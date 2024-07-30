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
    EXPIRED_TOKEN(401, "토큰이 만료되었습니다."),
    //-----------------------쓰레기통 관련 부분---------------------------
    NOT_FOUND_TRASHCAN(401, "등록된 쓰레기통이 없습니다"),
    NOT_FOUND_USERNAME(401, "등록된 사용자 이름이 없습니다"),
    NOT_FOUND_REST(401, "잔량 기록이 없습니다"),
    NOT_FOUND_SERIAL(401, "입력된 시리얼 넘버가 없습니다"),
    INVALID_LOCATION(401, "유효한 위치 범위가 아닙니다."),
    NOT_FOUND_NICKNAME(401, "입력된 닉네임이 없습니다"),
    INVALID_REST(401, "잔량은 0~100사이의 숫자로 입력해야합니다."),
    FAILED_TO_RESTLOG(100, "잔량 저장에 실패했습니다."),
    FAILED_TO_CATLOG(100, "카테고리 저장에 실패했습니다."),
    FAILED_TO_CATEGORYID(100, "카테고리 아이디가 전송되지 않았습니다."),
    NOT_FOUND_CATEGORYID(401, "DB에 저장된 카테고리 분류가 없습니다"),
    NOT_FOUND_CATEGORYNAME(401, "카테고리 이름을 조회할 수 없습니다"),
    //-----------------------숏컷 관련 부분---------------------------
    NOT_FOUND_SHORTCUT(401, "숏컷이 존재하지 않습니다."),
    USER_SHORTCUT_MISMATCH(401, "유저와 숏컷의 유저 정보가 일치하지 않습니다.");


    private int code;
    private String msg;

    ErrorType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ErrorResponse toErrorResponse() {
        return new ErrorResponse(this.code, this.msg);
    }

    public ErrorResponse toErrorResponse(String customMessage) {
        return new ErrorResponse(this.code, customMessage);
    }
}