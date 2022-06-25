package com.bm.membership.service.impl;

import com.bm.membership.component.ResultMappingComponent;
import com.bm.membership.domain.MemberShip;
import com.bm.membership.domain.Point;
import com.bm.membership.dto.api.common.History;
import com.bm.membership.dto.api.history.HistoryRequestDto;
import com.bm.membership.dto.api.history.HistoryResponseDto;
import com.bm.membership.dto.request.ApiRequest;
import com.bm.membership.dto.response.UserApiResponse;
import com.bm.membership.exception.ApiException;
import com.bm.membership.reasonkey.ResultType;
import com.bm.membership.service.ApiService;
import com.bm.membership.service.common.PointService;
import com.bm.membership.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.bm.membership.reasonkey.impl.CommonReasonKey.*;

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
@Transactional(readOnly = true)
public class HistoryService implements ApiService {

    private final Validator validator;

    private final ResultMappingComponent resultMappingComponent;

    private final PointService pointService;

    @Override
    public UserApiResponse executeApiService(ApiRequest apiRequest) {
        HistoryRequestDto historyRequestDto = (HistoryRequestDto) apiRequest;
        HistoryResponseDto historyResponseDto = new HistoryResponseDto();

        /**
         * 유효성 체크
         */
        paramCheck(historyRequestDto);

        /**
         * 바코드 체크
         */
        MemberShip memberShip = pointService.getMemberShip(historyRequestDto.getMembershipBarcode());

        /**
         * 포인트 사용내역 조회
         */
        List<Point> pointList = pointService.getPointHistory(historyRequestDto.getMembershipBarcode(), historyRequestDto.getStartDate(), historyRequestDto.getEndDate());

        /**
         * 응답 세팅
         */
        setResponseDto(pointList, historyResponseDto, SUCCESS);

        return historyResponseDto;
    }

    private void setResponseDto(List<Point> pointList, HistoryResponseDto historyResponseDto, ResultType resultType) {

        try {
            resultMappingComponent.setResponse(historyResponseDto, resultType);

            List<History> historyList = new ArrayList<>();

            pointList.forEach(point -> {
                        History history = History.builder()
                                .type(point.getUsageType())
                                .category(point.getCategory())
                                .partnerName(point.getPartnerName())
                                .point(point.getPointAmount())
                                .approvedAt(DateUtil.dateFormat(point.getApprovedAt()))
                                .build();
                historyList.add(history);
                    });

            historyResponseDto.setHistory(historyList);
        } catch (Exception e) {
            log.error("API_DATA_CREATING_ERROR : " + e);
            throw new ApiException(API_GENERAL_ERROR);
        }

    }

    /**
     * 요청값을 Dto 형태로 변환
     * @param param
     * @return
     */
    public HistoryRequestDto paramToDto(Map<String, String> param) {
        HistoryRequestDto historyRequestDto = null;

        try {
            historyRequestDto = HistoryRequestDto.builder()
                    .membershipBarcode(param.get("membership_barcode"))
                    .startDate(param.get("start_date"))
                    .endDate(param.get("end_date"))
                    .build();

        } catch (Exception e) {
            log.error("API_DATA_CREATING_ERROR : " + e);
            throw new ApiException(API_GENERAL_ERROR);

        }

        return historyRequestDto;
    }

    /**
     * 파라미터 유효성 체크
     * @param apiRequest 
     */
    private void paramCheck(ApiRequest apiRequest) {
        Set<ConstraintViolation<ApiRequest>> violations = validator.validate(apiRequest);

        if(!violations.isEmpty()) {
            log.error(apiRequest.getClass() + " VALIDATION CHECK FAILED - {}, -{}", apiRequest, violations);
            throw new ApiException(INVALID_PARAMETER);
        } else {
            log.info(apiRequest.getClass() + " VALIDATION CHECK SUCCEED- {}", apiRequest);
        }
    }
}

