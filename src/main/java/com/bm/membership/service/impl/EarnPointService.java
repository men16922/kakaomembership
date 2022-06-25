package com.bm.membership.service.impl;

import com.bm.membership.common.enums.UsageType;
import com.bm.membership.component.LockUUIDGenerator;
import com.bm.membership.component.ResultMappingComponent;
import com.bm.membership.domain.MemberPoint;
import com.bm.membership.domain.MemberShip;
import com.bm.membership.domain.Point;
import com.bm.membership.domain.Shop;
import com.bm.membership.dto.api.earn.EarnRequestDto;
import com.bm.membership.dto.api.earn.EarnResponseDto;
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
 * fileName       : PointService
 * author         : men16
 * date           : 2022-06-21
 * description    : Point 적립 Service
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21        men16       최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
public class EarnPointService implements ApiService {

    private final ResultMappingComponent resultMappingComponent;

    private final LockUUIDGenerator lockUUIDGenerator;

    private final PointRepository pointRepository;

    private final PointService pointService;

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public UserApiResponse executeApiService(ApiRequest apiRequest) {
        EarnRequestDto earnRequestDto = (EarnRequestDto) apiRequest;
        EarnResponseDto earnResponseDto = new EarnResponseDto();

        Shop shop = pointService.getShop(earnRequestDto.getPartnerId());

        MemberShip memberShip = pointService.getMemberShip(earnRequestDto.getMembershipBarcode());

        Point point = createAndSavePoint(earnRequestDto, shop, memberShip);

        MemberPoint memberPoint = pointService.createAndUpdateMemberPoint(memberShip, point, shop);

        setResponseDto(memberPoint, point, earnResponseDto, SUCCESS);


        return earnResponseDto;
                
    }

    /**
     * 응답 세팅
     * @param point 
     * @param earnResponseDto
     * @param resultType
     */
    private void setResponseDto(MemberPoint memberPoint, Point point, EarnResponseDto earnResponseDto, ResultType resultType) {
        resultMappingComponent.setResponse(earnResponseDto, resultType);
        earnResponseDto.setMembershipBarcode(point.getBarcode());
        earnResponseDto.setPoint(point.getPointAmount());
        earnResponseDto.setTotalPoint(memberPoint.getTotalPoint());
        earnResponseDto.setTid(point.getTid());
        earnResponseDto.setOrderId(point.getOrderId());
        earnResponseDto.setApprovedAt(DateUtil.dateFormat(point.getApprovedAt()));

    }


    /**
     * point 정보 생성 및 저장
     * @param earnRequestDto 
     * @param shop
     * @param memberShip
     * @return
     */
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Point createAndSavePoint(EarnRequestDto earnRequestDto, Shop shop, MemberShip memberShip){
        Point point = null;

        try {
            point = Point.builder()
                    .memberShip(memberShip)
                    .tid(lockUUIDGenerator.generateUUID())
                    .orderId(earnRequestDto.getOrderId())
                    .userId(memberShip.getUserId())
                    .partnerId(shop.getPartnerId())
                    .partnerName(shop.getPartnerName())
                    .barcode(memberShip.getBarcode())
                    .category(shop.getCategory())
                    .usageType(UsageType.earn)
                    .pointAmount(earnRequestDto.getPoint())
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

