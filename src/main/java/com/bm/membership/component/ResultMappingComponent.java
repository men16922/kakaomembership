package com.bm.membership.component;

import com.bm.membership.domain.response.CodeData;
import com.bm.membership.dto.response.UserApiResponse;
import com.bm.membership.exception.ApiException;
import com.bm.membership.reasonkey.ResultType;
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
 * description    : 응답 매핑 컴포넌트
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

    public UserApiResponse setResponse(UserApiResponse userApiResponse, ResultType resultType) {
        CodeData codeData = codeDataRepository.findByReasonKey(resultType.value()).orElseThrow(() -> new ApiException(API_GENERAL_ERROR));
        userApiResponse.setReasonKey(resultType.value());
        userApiResponse.setResultCode(codeData.getErrorCode());
        userApiResponse.setResultMessage(codeData.getErrorMsg());

        return userApiResponse;
    }
}

