package com.ssafy.teongbin.common.reseponse;

import lombok.Getter;

@Getter
public enum MsgType {

    SIGNUP_SUCCESSFULLY("회원가입이 완료되었습니다."),
    LOGIN_SUCCESSFULLY("로그인이 완료되었습니다."),
    SEARCH_SUCCESSFULLY("조회 성공"),
    DATA_SUCCESSFULLY("데이터 생성 성공"),
    ADD_SHORTCUT_SUCCESSFULLY("숏컷 등록 성공"),
    SEARCH_SHORTCUT_LIST_SUCCESSFULLY("숏컷 리스트 조회 성공"),
    DELETE_SHORTCUT_SUCCESSFULLY("숏컷 삭제 성공"),
    UPDATE_SUCCESSFULLY("수정 성공"),
    GENERATE_TOKEN_SUCCESSFULLY("토큰 생성 성공"),
    SEARCH_TRASH_LIST_SUCCESSFULLY("쓰레기통 리스트 조회 성공"),
    SEARCH_REST_LIST_SUCCESSFULLY("잔량 조회 성공"),
    SEARCH_CAT_LOG_SUCCESSFULLY("카테고리 통계 조회 성공"),
    //==================== 쓰레기통
    DELETE_TRASHCAN_SUCCESSFULLY("쓰레기통 삭제 성공"),
    ADD_TRASHCAN_SUCCESSFULLY("쓰레기통 등록 성공"),
    UPDATE_TRASHCAN_SUCCESSFULLY("쓰레기통 수정 성공");
    private final String msg;

    MsgType(String msg){
        this.msg = msg;
    }
}
