package com.bm.membership.domain;

import com.bm.membership.common.enums.Category;
import com.bm.membership.common.enums.UsageType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * packageName    : com.bm.membership.domain
 * fileName       : Point
 * author         : men16
 * date           : 2022-06-20
 * description    : 포인트 내역 도메인
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20        men16       최초 생성
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "POINT", uniqueConstraints = @UniqueConstraint(columnNames = { "tid", "userId"}))
public class Point {

    @Id
    @GeneratedValue
    @Column(name = "POINT_SEQ", nullable = false)
    @Schema(name = "POINT_SEQ")
    private Long pointSeq;

    @ManyToOne
    @JoinColumn(name = "MEMBERSHIP_SEQ", nullable = false)
    private MemberShip memberShip;

    @Column(name = "TID", nullable = false)
    @Schema(name = "거래ID")
    private String tid;

    @Column(name = "USER_ID", nullable = false)
    @Schema(name = "사용자 ID")
    private Long userId;

    @Column(name = "BARCODE", nullable = false)
    @Schema(name = "바코드")
    private String barCode;

    @Column(name = "CATEGORY", nullable = false)
    @Schema(name = "업종")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "TYPE", nullable = false)
    @Schema(name = "사용타입")
    @Enumerated(EnumType.STRING)
    private UsageType usageType;

    @Column(name = "POINT_AMOUNT", nullable = false)
    @Schema(name = "포인트사용금액")
    private String point;

    @Column(name = "APPRVED_AT", nullable = false)
    @Schema(name = "사용일")
    private LocalDate approvedAt;
}

