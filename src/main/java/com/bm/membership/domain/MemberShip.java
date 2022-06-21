package com.bm.membership.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "MEMBERSHIP", uniqueConstraints = @UniqueConstraint(columnNames = { "tid", "userId", "barcode"}))
public class MemberShip {

    @Id
    @GeneratedValue
    @Column(name = "MEMBERSHIP_SEQ", nullable = false)
    @Schema(name = "MEMBERSHIP_SEQ")
    private Long memberShipSeq;

    @ManyToOne
    @JoinColumn(name = "SHOP_SEQ", nullable = false)
    private Shop shop;

    @Column(name = "TID", nullable = false)
    @Schema(name = "거래ID")
    private String tid;

    @Column(name = "USER_ID", nullable = false)
    @Schema(name = "사용자 ID")
    private Long userId;

    @Column(name = "BARCODE", nullable = false)
    @Schema(name = "바코드")
    private String barCode;

    @Column(name = "RGSTED_AT", nullable = false)
    @Schema(name = "가입일")
    private LocalDate registeredAt;

    @JsonIgnore
    @OneToMany(mappedBy = "memberShip")
    private List<Point> point;

    @JsonIgnore
    @OneToOne(mappedBy = "memberShip")
    private MemberPoint memberPoint;
}

