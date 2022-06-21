package com.bm.membership.advice;

import com.bm.membership.dto.CommonResponse;
import com.bm.membership.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.bm.membership.reasonkey.impl.CommonReasonKey.API_GENERAL_ERROR;

/**
 * packageName    : com.bm.membership.advice
 * fileName       : GlobalRestControllerAdvice
 * author         : men16
 * date           : 2022-06-20
 * description    : Excetpion 처리 Advice
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20        men16       최초 생성
 */

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class GlobalRestControllerAdvice {

    @ExceptionHandler(ApiException.class)
    public CommonResponse handleConflict(ApiException exception) {

        CommonResponse response = new CommonResponse();
        response.setReasonKey(exception.getReasonKey());
        response.setResultCode(exception.getReturnCode());
        response.setResultMessage(exception.getReturnMessage());

        return response;
    }

    @ExceptionHandler({Exception.class})
    public CommonResponse othersExceptionHandler(Exception exception) {

        CommonResponse response = new CommonResponse();
        response.setReasonKey(API_GENERAL_ERROR.value());
        response.setResultCode("99999");
        response.setResultMessage("Common Error");

        log.debug("Common Error 발생");
        exception.printStackTrace();

        return response;
    }
}

