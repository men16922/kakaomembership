package com.bm.membership.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * packageName    : com.bm.membership.domain
 * fileName       : MemberShip
 * author         : men16
 * date           : 2022-06-20
 * description    : MEMBERSHIP 도메인
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20        men16       최초 생성
 */
@SequenceGenerator(
        name = "MEMBERSHIP_GENERATOR",
        sequenceName = "MEMBERSHIP_SEQUENCES",
        initialValue = 1, allocationSize = 1)
@ToString(exclude = {"point", "memberPoint"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "MEMBERSHIP", uniqueConstraints = @UniqueConstraint(columnNames = { "TID", "USER_ID", "BARCODE"}))
public class MemberShip {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "MEMBERSHIP_GENERATOR")
    @Column(name = "MEMBERSHIP_SEQ", nullable = false)
    @Schema(name = "MEMBERSHIP_SEQ")
    private Long memberShipSeq;

    @Column(name = "TID", nullable = false)
    @Schema(name = "거래ID")
    private String tid;

    @Column(name = "ORDERID", nullable = false)
    @Schema(name = "주문ID")
    private String orderId;

    @Column(name = "USER_ID", nullable = false)
    @Schema(name = "사용자 ID")
    private Long userId;

    @Column(name = "BARCODE", nullable = false)
    @Schema(name = "바코드")
    private String barcode;

    @Column(name = "RGSTED_AT")
    @Schema(name = "가입일")
    private LocalDateTime registeredAt;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "memberShip")
    private List<Point> point;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "memberShip")
    private List<MemberPoint> memberPoint;

}

