package com.bm.membership.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;

/**
 * packageName    : com.bm.membership.domain
 * fileName       : MemberPoint
 * author         : men16
 * date           : 2022-06-21
 * description    : MEMBER_POINT 도메인
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21        men16       최초 생성
 */
@SequenceGenerator(
        name = "MEMBER_POINT_GENERATOR",
        sequenceName = "MEMBER_POINT_SEQUENCES",
        initialValue = 1, allocationSize = 1)
@ToString(exclude = {"memberShip", "shop"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "MEMBER_POINT")
public class MemberPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_POINT_GENERATOR")
    @Column(name = "MEMBER_POINT_SEQ", nullable = false)
    @Schema(name = "MEMBER_POINT_SEQ")
    private Long memberPointSeq;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private MemberShip memberShip;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY", nullable = false)
    private Shop shop;

    @Column(name = "CATEGORY_ID", nullable = false)
    @Schema(name = "업종")
    private String categoryId;

    @Column(name = "TOTAL_POINT", nullable = false)
    @Schema(name = "포인트총합")
    private Float totalPoint;

    @Version
    @Column(name = "VERSION", nullable = false)
    @Schema(name = "버전")
    private Long version;

}

