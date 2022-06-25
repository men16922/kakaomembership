package com.bm.membership.exception;

import com.bm.membership.reasonkey.ResultType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName    : com.bm.membership.exception
 * fileName       : ApiException
 * author         : men16
 * date           : 2022-06-20
 * description    : API 실패 처리용 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20        men16       최초 생성
 */
@Setter
@Getter
@ToString
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 3263621656521309597L;

    private ResultType failType;

    private String reasonKey;

    private String returnCode;

    private String returnMessage;

    public ApiException(ResultType failType) {
        super(failType.value());
        this.reasonKey = failType.value();
        this.failType = failType;

    }

    public ApiException(String reasonKey, String returnMessage) {
        super(reasonKey);
        this.reasonKey = reasonKey;
        this.returnMessage = returnMessage;

    }

    public ApiException(ResultType failType, String message) {
        super(String.format("[%s]%s", failType.value(), message));
        this.reasonKey = failType.value();
        this.returnMessage = message;

    }


}
