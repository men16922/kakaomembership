package com.bm.membership.advice;

import com.bm.membership.domain.response.CodeData;
import com.bm.membership.dto.CommonResponseUser;
import com.bm.membership.exception.ApiException;
import com.bm.membership.repository.response.CodeDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.bm.membership.reasonkey.impl.CommonReasonKey.API_GENERAL_ERROR;
import static com.bm.membership.reasonkey.impl.CommonReasonKey.INVALID_PARAMETER;

/**
 * packageName    : com.bm.membership.advice
 * fileName       : GlobalRestControllerAdvice
 * author         : men16
 * date           : 2022-06-20
 * description    : Exception 처리 Advice
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20        men16       최초 생성
 */

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice(annotations = RestController.class)
public class GlobalRestControllerAdvice {

    private final CodeDataRepository codeDataRepository;

    @ExceptionHandler(ApiException.class)
    public CommonResponseUser handleConflict(ApiException exception) throws Exception {

        CommonResponseUser response = new CommonResponseUser();
        CodeData codeData = codeDataRepository.findByReasonKey(exception.getReasonKey()).orElse(null);
        if(codeData != null) {
            response.setReasonKey(codeData.getReasonKey());
            response.setResultCode(codeData.getErrorCode());
            response.setResultMessage(codeData.getErrorMsg());
            if(exception.getReturnMessage() != null)
                response.setResultMessage(response.getResultMessage() + ", " + exception.getReturnMessage());

        } else {
            response.setReasonKey(API_GENERAL_ERROR.value());
            response.setResultCode(CommonEnum.API_GENERAL_ERROR.getErrorCode());
            response.setResultMessage("Api general error");
        }

        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponseUser methodArgumentExceptionHandler(MethodArgumentNotValidException exception) {

        BindingResult bindingResult = exception.getBindingResult();

        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append("[");
            builder.append((fieldError.getField()));
            builder.append("] parameter ");
            builder.append(fieldError.getDefaultMessage());
            builder.append(", Input Value: [");
            builder.append(fieldError.getRejectedValue());
            builder.append("]");
        }
        String resultMessage = builder.toString();

        CommonResponseUser response = new CommonResponseUser();
        response.setReasonKey(INVALID_PARAMETER.value());
        response.setResultCode(CommonEnum.INVALID_PARAMETER.getErrorCode());
        response.setResultMessage(resultMessage);

        log.info("Parameter Validation Error : " + resultMessage);

        return response;
    }

    @ExceptionHandler({Throwable.class})
    public CommonResponseUser othersExceptionHandler(Exception exception) {

        CommonResponseUser response = new CommonResponseUser();
        response.setReasonKey(API_GENERAL_ERROR.value());
        response.setResultCode(CommonEnum.API_GENERAL_ERROR.getErrorCode());
        response.setResultMessage("Api general error");

        log.info("Api general error");

        return response;
    }
}

