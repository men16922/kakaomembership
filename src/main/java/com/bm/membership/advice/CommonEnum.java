package com.bm.membership.advice;

/**
 * packageName    : com.bm.membership.advice
 * fileName       : CommonEnum
 * author         : men16
 * date           : 2022-06-22
 * description    : 일반적인 예외처리시 사용하는 enum
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-22        men16       최초 생성
 */
public enum CommonEnum {

    INVALID_PARAMETER("00001"),
    API_GENERAL_ERROR("99999");

    final private String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    private CommonEnum(String errorCode) {
        this.errorCode = errorCode;
    }
}
