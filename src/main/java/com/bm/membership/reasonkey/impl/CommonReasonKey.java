package com.bm.membership.reasonkey.impl;

import com.bm.membership.reasonkey.ResultType;

/**
 * packageName    : com.bm.membership.reasonkey.impl
 * fileName       : CommonReasonKey
 * author         : men16
 * date           : 2022-06-20
 * description    : CODE_DATE와 매핑되는 에러 키값
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20        men16       최초 생성
 */

public enum CommonReasonKey implements ResultType {
    SUCCESS("SUCCESS"), //성공 00000
    INVALID_PARAMETER("INVALID_PARAMETER"), //잘못된 파라미터 00001
    REGISTERED_ID("REGISTERED_ID"), // 기등록 USERID 00002
    NO_PARTNER("NO_PARTNER"), // 상점 존재 X 00003
    NO_BARCODE("NO_BARCODE"), // 바코드 존재 X 00004
    NOT_ENOUGH_POINT("NOT_ENOUGH_POINT"), // 잔액 부족 00005
    NO_POINT_HISTORY("NO_POINT_HISTORY"), // 포인트 내역 X 00005
    TRANSACTION_ERROR("TRANSACTION_ERROR"), // 트랜잭션 오류 99998
    API_GENERAL_ERROR("API_GENERAL_ERROR") //기타오류 99999
    ;

    private String value;

    CommonReasonKey(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }
}

