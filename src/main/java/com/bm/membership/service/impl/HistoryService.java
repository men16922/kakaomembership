package com.bm.membership.service.impl;

import com.bm.membership.dto.api.history.HistoryRequestDto;
import com.bm.membership.dto.request.ApiRequest;
import com.bm.membership.dto.response.ApiResponse;
import com.bm.membership.exception.ApiException;
import com.bm.membership.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static com.bm.membership.reasonkey.impl.CommonReasonKey.API_DATA_CREATING_ERROR;
import static com.bm.membership.reasonkey.impl.CommonReasonKey.INVALID_PARAMETER;

/**
 * packageName    : com.bm.membership.service.impl
 * fileName       : HistoryService
 * author         : men16
 * date           : 2022-06-21
 * description    : 사용 내역 조회 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21        men16       최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class HistoryService implements ApiService {

    private final Validator validator;

    @Override
    public ApiResponse executeApiService(ApiRequest apiRequest) {
        paramCheck(apiRequest);

        return null;
    }

    public HistoryRequestDto paramToDto(MultiValueMap<String, String> param) {
        HistoryRequestDto historyRequestDto = null;

        try {
            historyRequestDto = HistoryRequestDto.builder()
                    .membershipBarcode(param.getFirst("membership_barcode"))
                    .startDate(param.getFirst("start_date"))
                    .endDate(param.getFirst("end_date"))
                    .build();

        } catch (Exception e) {
            log.error("API_DATA_CREATING_ERROR : " + e);
            throw new ApiException(API_DATA_CREATING_ERROR);

        }

        return historyRequestDto;
    }

    private void paramCheck(ApiRequest apiRequest) {
        Set<ConstraintViolation<ApiRequest>> violations = validator.validate(apiRequest);

        if(!violations.isEmpty()) {
            log.error(apiRequest.getClass() + " VALIDATION CHECK FAILED - {}", apiRequest);
            throw new ApiException(INVALID_PARAMETER);
        } else {
            log.info(apiRequest.getClass() + " VALIDATION CHECK SUCCEED- {}", apiRequest);
        }
    }
}

