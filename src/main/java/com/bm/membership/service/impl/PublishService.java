package com.bm.membership.service.impl;

import com.bm.membership.component.BarcodeGenerator;
import com.bm.membership.component.LockUUIDGenerator;
import com.bm.membership.component.ResultMappingComponent;
import com.bm.membership.domain.MemberShip;
import com.bm.membership.dto.api.publish.PublishRequestDto;
import com.bm.membership.dto.api.publish.PublishResponseDto;
import com.bm.membership.dto.request.ApiRequest;
import com.bm.membership.dto.response.UserApiResponse;
import com.bm.membership.exception.ApiException;
import com.bm.membership.reasonkey.ResultType;
import com.bm.membership.repository.MemberShipRepository;
import com.bm.membership.service.ApiService;
import com.bm.membership.util.DateUtil;
import com.bm.membership.util.ParamUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.bm.membership.reasonkey.impl.CommonReasonKey.*;

/**
 * packageName    : com.bm.membership.service
 * fileName       : PublishService
 * author         : men16
 * date           : 2022-06-21
 * description    : 통합 바코드 발급 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21        men16       최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PublishService implements ApiService {

    private final BarcodeGenerator barcodeGenerator;

    private final MemberShipRepository memberShipRepository;

    private final ResultMappingComponent resultMappingComponent;

    private final LockUUIDGenerator lockUUIDGenerator;

    @Override
    public UserApiResponse executeApiService(ApiRequest apiRequest) {

        PublishRequestDto publishRequestDto = (PublishRequestDto) apiRequest;
        PublishResponseDto publishResponseDto = new PublishResponseDto();

        if(ParamUtil.str(publishRequestDto.getUserId()).length() != 9) {
            log.error("[PUBLISH] REQUEST DTO ERROR, USER ID LENGTH - {}", ParamUtil.str(publishRequestDto.getUserId()).length());
            throw new ApiException(INVALID_PARAMETER, "USER ID LENGTH : " + ParamUtil.str(publishRequestDto.getUserId()).length());
        }

        /**
         * user_id가 중복되는 경우 기존 멤버십 코드 반환
         */
        if(checkDuplicatedUserId(publishRequestDto, publishResponseDto)) {
            return publishResponseDto;

        } else {
            writeMemberShip(publishRequestDto, publishResponseDto);
        }

        return publishResponseDto;

    }

    /**
     * 응답 세팅
     * @param memberShip 
     * @param publishResponseDto
     * @param resultType
     */
    private void setResponseDto(MemberShip memberShip, PublishResponseDto publishResponseDto, ResultType resultType) {

        resultMappingComponent.setResponse(publishResponseDto, resultType);
        publishResponseDto.setMembershipBarcode(memberShip.getBarcode());
        publishResponseDto.setOrderId(memberShip.getOrderId());
        publishResponseDto.setTid(memberShip.getTid());
        publishResponseDto.setRegisteredAt(DateUtil.dateFormat(memberShip.getRegisteredAt()));

    }

    /**
     * 중복 ID 체크
     * @param publishRequestDto 
     * @param publishResponseDto
     * @return
     */
    private boolean checkDuplicatedUserId(PublishRequestDto publishRequestDto, PublishResponseDto publishResponseDto) {
        MemberShip memberShip = memberShipRepository.findByUserId(publishRequestDto.getUserId()).orElse(null);

        if(memberShip != null) {
            log.info("[PUBLISH] USER ID ALREADY REGISTERED - {}", publishRequestDto.getUserId());
            log.info("[PUBLISH] memberShip - {}", memberShip);
            setResponseDto(memberShip, publishResponseDto, REGISTERED_ID);
            log.info("[PUBLISH] PUBLISH RESPONSE - {}", publishResponseDto);

            return true;

        } else {
            log.info("[PUBLISH] USER ID NOT EXIST");
            return false;
        }
    }

    /**
     * 멤버십 정보 저장, 응답값 지정
     * @param publishRequestDto 
     * @param publishResponseDto
     */
    private void writeMemberShip(PublishRequestDto publishRequestDto, PublishResponseDto publishResponseDto) {

        MemberShip memberShip = null;

        try {
            memberShip = MemberShip.builder()
                    .tid(lockUUIDGenerator.generateUUID())
                    .orderId(publishRequestDto.getOrderId())
                    .userId(publishRequestDto.getUserId())
                    .barcode(barcodeGenerator.createBarcode())
                    .registeredAt(LocalDateTime.now())
                    .build();

        } catch (Exception e) {
            log.error("MEMBERSHIP DATA CREATE FAILED : " + e);
            throw new ApiException(API_GENERAL_ERROR);

        }

        try {
            memberShipRepository.save(memberShip);
            setResponseDto(memberShip, publishResponseDto, SUCCESS);

        } catch (Exception e) {
            log.error("MEMBERSHIP SAVE FAILED : " + e);
            throw new ApiException(TRANSACTION_ERROR);

        }

    }
}

