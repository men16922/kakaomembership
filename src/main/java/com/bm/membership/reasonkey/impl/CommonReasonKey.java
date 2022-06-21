package com.bm.membership.reasonkey.impl;

import com.bm.membership.reasonkey.ResultType;

/**
 * packageName    : com.bm.membership.reasonkey.impl
 * fileName       : CommonReasonKey
 * author         : men16
 * date           : 2022-06-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20        men16       최초 생성
 */

public enum CommonReasonKey implements ResultType {
    SUCCESS("SUCCESS"), //성공 00000
    INVALID_PARAMETER("INVALID_PARAMETER"), //잘못된 파라미터 00001
    API_DATA_CREATING_ERROR("API_DATA_CREATING_ERROR"), //API데이터 생성 오류 29999
    API_GENERAL_ERROR("API_GENERAL_ERROR") //기타오류 29999
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

