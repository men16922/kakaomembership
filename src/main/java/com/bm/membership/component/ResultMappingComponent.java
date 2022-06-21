package com.bm.membership.component;

import com.bm.membership.domain.response.CodeData;
import com.bm.membership.dto.response.ApiResponse;
import com.bm.membership.exception.ApiException;
import com.bm.membership.repository.response.CodeDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.bm.membership.reasonkey.impl.CommonReasonKey.API_GENERAL_ERROR;

/**
 * packageName    : com.bm.membership.service
 * fileName       : ResultMappingService
 * author         : men16
 * date           : 2022-06-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20        men16       최초 생성
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ResultMappingComponent {

    private final CodeDataRepository codeDataRepository;

    public ApiResponse setResponse(ApiResponse apiResponse, String reasonKey) {
        CodeData codeData = codeDataRepository.findByReasonKey(reasonKey).orElseThrow(() -> new ApiException(API_GENERAL_ERROR));
        apiResponse.setReasonKey(reasonKey);
        apiResponse.setResultCode(codeData.getErrorCode());
        apiResponse.setResultMessage(codeData.getErrorMsg());

        return apiResponse;
    }
}

