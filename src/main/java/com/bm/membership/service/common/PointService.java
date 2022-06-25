package com.bm.membership.service.common;

import com.bm.membership.common.enums.Category;
import com.bm.membership.common.enums.UsageType;
import com.bm.membership.domain.MemberPoint;
import com.bm.membership.domain.MemberShip;
import com.bm.membership.domain.Point;
import com.bm.membership.domain.Shop;
import com.bm.membership.exception.ApiException;
import com.bm.membership.repository.MemberPointRepository;
import com.bm.membership.repository.MemberShipRepository;
import com.bm.membership.repository.PointRepository;
import com.bm.membership.repository.ShopRepository;
import com.bm.membership.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.bm.membership.reasonkey.impl.CommonReasonKey.*;

/**
 * packageName    : com.bm.membership.service
 * fileName       : PointService
 * author         : men16
 * date           : 2022-06-25
 * description    : 포인트 적립/사용 공통 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-25        men16       최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
public class PointService{

    private final ShopRepository shopRepository;

    private final MemberShipRepository memberShipRepository;

    private final MemberPointRepository memberPointRepository;

    private final PointRepository pointRepository;

    /**
     * 잔액 확인
     * @param memberPoint
     * @param point
     */
    private float remainAmountCheck(MemberPoint memberPoint, Point point) {
        float currentPoint = 0;

        if(memberPoint == null) {
            if(point.getUsageType() == UsageType.earn) {
                log.info("INSERT NEW MEMBER POINT");
            } else {
                log.error("MEMBER POINT USAGE REQUEST BEFORE EARN");
                throw new ApiException(NOT_ENOUGH_POINT, "needs to deposit point first");

            }
        } else {
            currentPoint = memberPoint.getTotalPoint();
            if(point.getUsageType() == UsageType.use) {
                currentPoint = currentPoint - point.getPointAmount();
            } else {
                currentPoint = currentPoint + point.getPointAmount();
            }
            if(currentPoint < 0) {
                log.error("MEMBER CURRENT POINT IS NOT SUFFICIENT, REMAIN -{}, REQUEST USE -{} :  " + memberPoint.getTotalPoint(), point.getPointAmount());
                throw new ApiException(NOT_ENOUGH_POINT, String.format("saved point : %.2f, use point : %.2f", memberPoint.getTotalPoint(), point.getPointAmount()));

            }
        }

        return currentPoint;
    }

    /**
     * 멤버 포인트 적립 정보 업데이트
     * @param point
     * @param shop
     * @return
     */
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public MemberPoint createAndUpdateMemberPoint(MemberShip memberShip, Point point, Shop shop) {

        MemberPoint memberPoint = null;

        try {

            memberPoint = memberPointRepository.findByShop(Category.findBy(shop.getCategory().name())).orElse(null);
            log.info("CURRENT MEMBER POINT - {}" , memberPoint);



            /**
             * 이미 memberPoint에 존재하면 update
             */
            if(memberPoint != null) {
                log.info("=== UPDATE MEMBER POINT ===");
                float currentPoint = remainAmountCheck(memberPoint, point);
                memberPoint.setTotalPoint(currentPoint);

            } else {
                log.info("=== INSERT MEMBER POINT === ");

                remainAmountCheck(null, point);

                memberPoint = MemberPoint.builder()
                        .memberShip(memberShip)
                        .shop(shop)
                        .categoryId(Category.findBy(shop.getCategory().name()))
                        .totalPoint(point.getPointAmount())
                        .build();
            }

            log.info("MEMBER POINT DATA - {}", memberPoint);

        } catch (ApiException e) {
            throw new ApiException(e.getReasonKey(), e.getReturnMessage());

        } catch (Exception e) {
            log.error("MEMBER POINT DATA CREATE FAILED : " + e);
            throw new ApiException(API_GENERAL_ERROR);

        }

        try {
            memberPointRepository.save(memberPoint);

        } catch (Exception e) {
            log.error("MEMBER POINT SAVE FAILED : " + e);
            throw new ApiException(TRANSACTION_ERROR);

        }

        return memberPoint;
    }

    /**
     * 사용 내역 조회
     * @param startDate 
     * @param endDate
     * @param barcode
     * @return
     */
    @Transactional(readOnly = true)
    public List<Point> getPointHistory(String barcode, String startDate, String endDate) {
        List<Point> pointList = null;

        try {
            LocalDateTime startTime = DateUtil.toLocalDateTime(startDate);
            LocalDateTime endTime = DateUtil.toLocalDateTime(endDate);
            if(startTime.isEqual(endTime)) {
                endTime = endTime.plusDays(1);
            }
            pointList = pointRepository.findByBarcodeAndDates(barcode, startTime, endTime).orElse(null);

            if(pointList.isEmpty()) {
                log.error("REQUESTED BARCODE DOES NOT HAVE ANY POINT LIST");
                throw new ApiException(NO_POINT_HISTORY);
            }

            for(Point point : pointList) {
                log.info("POINT - {}" , point);
            }

        } catch (ApiException e) {
            log.error("SELECT POINT LIST FAILED : " + e);
            throw new ApiException(NO_POINT_HISTORY);

        } catch (Exception e) {
            log.error("SELECT POINT LIST FAILED :" + e);
            throw new ApiException(TRANSACTION_ERROR);
        }

        return pointList;
    }


    /**
     * 상점ID로 상점정보 조회
     * @param partnerId
     * @return
     */
    @Transactional(readOnly = true)
    public Shop getShop(String partnerId) {
        Shop shop = null;
        try {
            shop = shopRepository.findByPartnerId(partnerId).orElse(null);

            if(shop == null) {
                log.error("SHOP PARTNER ID DOES NOT EXIST - {}" , partnerId);
                throw new ApiException(NO_PARTNER);
            }

            log.info("SHOP INFO - {}", shop);

        } catch (ApiException e) {
            throw new ApiException(NO_PARTNER);

        } catch (Exception e) {
            log.error("SELECT SHOP FAILED :" + e);
            throw new ApiException(TRANSACTION_ERROR);

        }

        return shop;
    }

    /**
     * 바코드로 멤버십 정보 조회
     * @param barcode
     * @return
     */
    @Transactional(readOnly = true)
    public MemberShip getMemberShip(String barcode) {
        MemberShip memberShip = null;
        try {
            memberShip = memberShipRepository.findByBarcode(barcode).orElse(null);

            if(memberShip == null) {
                log.error("MEMBERSHIP BARCODE DOES NOT EXIST - {}" , barcode);
                throw new ApiException(NO_BARCODE);
            }

            log.info("MEMBERSHIP INFO - {}", memberShip);

        } catch (ApiException e) {
            throw new ApiException(NO_BARCODE);

        } catch (Exception e) {
            log.error("SELECT MEMBER FAILED :" + e);
            throw new ApiException(TRANSACTION_ERROR);

        }

        return memberShip;
    }
}

