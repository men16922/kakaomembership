package com.bm.membership.domain;

import com.bm.membership.common.enums.Category;
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

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "MEMBER_POINT")
public class MemberPoint {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_POINT_SEQ", nullable = false)
    @Schema(name = "MEMBER_POINT_SEQ")
    private Long memberPointSeq;

    @OneToOne
    @JoinColumn(name = "MEMBERSHIP_SEQ", nullable = false)
    private MemberShip memberShip;

    @Column(name = "CATEGORY", nullable = false)
    @Schema(name = "업종")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "TOTAL_POINT", nullable = false)
    @Schema(name = "포인트총합")
    private String totalPoint;

    @Version
    @Column(name = "VERSION", nullable = false)
    @Schema(name = "버전")
    private Long version;
}

