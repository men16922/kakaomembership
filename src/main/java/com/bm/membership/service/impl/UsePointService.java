package com.bm.membership.service.impl;

import com.bm.membership.common.enums.UsageType;
import com.bm.membership.component.LockUUIDGenerator;
import com.bm.membership.component.ResultMappingComponent;
import com.bm.membership.domain.MemberPoint;
import com.bm.membership.domain.MemberShip;
import com.bm.membership.domain.Point;
import com.bm.membership.domain.Shop;
import com.bm.membership.dto.api.use.UseRequestDto;
import com.bm.membership.dto.api.use.UseResponseDto;
import com.bm.membership.dto.request.ApiRequest;
import com.bm.membership.dto.response.UserApiResponse;
import com.bm.membership.exception.ApiException;
import com.bm.membership.reasonkey.ResultType;
import com.bm.membership.repository.PointRepository;
import com.bm.membership.service.ApiService;
import com.bm.membership.service.common.PointService;
import com.bm.membership.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.bm.membership.reasonkey.impl.CommonReasonKey.*;

/**
 * packageName    : com.bm.membership.service.impl
 * fileName       : UsePointService
 * author         : men16
 * date           : 2022-06-21
 * description    : Point 사용 Service
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21        men16       최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
public class UsePointService implements ApiService {

    private final ResultMappingComponent resultMappingComponent;

    private final LockUUIDGenerator lockUUIDGenerator;

    private final PointRepository pointRepository;

    private final PointService pointService;

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public UserApiResponse executeApiService(ApiRequest apiRequest) {
        UseRequestDto useRequestDto = (UseRequestDto) apiRequest;
        UseResponseDto useResponseDto = new UseResponseDto();

        Shop shop = pointService.getShop(useRequestDto.getPartnerId());

        MemberShip memberShip = pointService.getMemberShip(useRequestDto.getMembershipBarcode());

        Point point = createAndSavePoint(useRequestDto, shop, memberShip);

        MemberPoint memberPoint = pointService.createAndUpdateMemberPoint(memberShip, point, shop);

        setResponseDto(memberPoint, point, useResponseDto, SUCCESS);

        return useResponseDto;
    }


    /**
     * 응답 세팅
     * @param point
     * @param useResponseDto
     * @param resultType
     */
    private void setResponseDto(MemberPoint memberPoint, Point point, UseResponseDto useResponseDto, ResultType resultType) {
        resultMappingComponent.setResponse(useResponseDto, resultType);
        useResponseDto.setMembershipBarcode(point.getBarcode());
        useResponseDto.setPoint(point.getPointAmount());
        useResponseDto.setTotalPoint(memberPoint.getTotalPoint());
        useResponseDto.setTid(point.getTid());
        useResponseDto.setOrderId(point.getOrderId());
        useResponseDto.setApprovedAt(DateUtil.dateFormat(point.getApprovedAt()));

    }

    /**
     * point 정보 생성 및 저장
     * @param useRequestDto
     * @param shop
     * @param memberShip
     * @return
     */
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Point createAndSavePoint(UseRequestDto useRequestDto, Shop shop, MemberShip memberShip){
        Point point = null;

        try {
            point = Point.builder()
                    .memberShip(memberShip)
                    .tid(lockUUIDGenerator.generateUUID())
                    .orderId(useRequestDto.getOrderId())
                    .userId(memberShip.getUserId())
                    .partnerId(shop.getPartnerId())
                    .partnerName(shop.getPartnerName())
                    .barcode(memberShip.getBarcode())
                    .category(shop.getCategory())
                    .usageType(UsageType.use)
                    .pointAmount(useRequestDto.getPoint())
                    .approvedAt(LocalDateTime.now())
                    .build();

        } catch (Exception e) {
            log.error("POINT DATA CREATE FAILED : " + e);
            throw new ApiException(API_GENERAL_ERROR);

        }

        try {
            pointRepository.save(point);

        } catch (Exception e) {
            log.error("POINT SAVE FAILED : " + e);
            throw new ApiException(TRANSACTION_ERROR);

        }

        log.info("POINT INSERT SUCCEED : "  + point);

        return point;
    }
}

