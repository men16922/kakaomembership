package com.bm.membership.transaction;

import com.bm.membership.common.enums.Category;
import com.bm.membership.common.enums.UsageType;
import com.bm.membership.controller.PointController;
import com.bm.membership.domain.MemberPoint;
import com.bm.membership.domain.MemberShip;
import com.bm.membership.domain.Point;
import com.bm.membership.domain.Shop;
import com.bm.membership.repository.MemberPointRepository;
import com.bm.membership.service.common.PointService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * packageName    : com.bm.membership.transaction
 * fileName       : TransactionTest
 * author         : men16
 * date           : 2022-06-25
 * description    : 트랜잭션 테스트(미완)
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-25        men16       최초 생성
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
public class TransactionTest {

    private MockMvc mockMvc;

    @InjectMocks
    private PointController pointController;

    @InjectMocks
    private PointService pointService;

    @Mock
    private MemberPointRepository memberPointRepository;

    @BeforeAll
    public void setUp() {

        mockMvc = MockMvcBuilders
                .standaloneSetup(pointController)
                .build();
    }

    @Test
    public void a() {

        Shop shop = Shop.builder()
                .shopSeq(7l)
                .category(Category.A)
                .partnerId("testshop")
                .partnerName("테스트상점")
                .build();

        MemberShip memberShip = MemberShip.builder()
                .memberShipSeq(100000l)
                .userId(200000000l)
                .tid("TESTTID")
                .orderId("TESTORDERID")
                .barcode("ABCDEFGHIJ")
                .registeredAt(LocalDateTime.now())
                .build();


        MemberPoint memberPoint = MemberPoint.builder()
                .memberShip(memberShip)
                .shop(shop)
                .categoryId("GROCERY")
                .totalPoint(1000f)
                .version(2l)
                .build();

        Point point = Point.builder()
                .memberShip(memberShip)
                .tid("tid")
                .orderId("orderid")
                .partnerId("testshop")
                .partnerName("테스트상점")
                .userId(200000000l)
                .barcode("ABCDEFGHIJ")
                .category(Category.A)
                .usageType(UsageType.earn)
                .pointAmount(1000f)
                .approvedAt(LocalDateTime.now())
                .build();



        // when
        pointService.createAndUpdateMemberPoint(memberShip, point, shop);


        Mockito.verify(memberPointRepository, Mockito.times(1))
                .save(Mockito.any(MemberPoint.class));
    }
}

