package com.bm.membership.domain;

import com.bm.membership.common.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * packageName    : com.bm.membership.domain
 * fileName       : Shop
 * author         : men16
 * date           : 2022-06-20
 * description    : 상점 도메인
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
@Table(name = "SHOP")
public class Shop {

    @Id
    @GeneratedValue
    @Column(name = "SHOP_SEQ", nullable = false)
    @Schema(name = "SHOP_SEQ")
    private Long shopSeq;

    @Column(name = "CATEGORY", nullable = false)
    @Schema(name = "업종")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "PARTNER_ID", nullable = false)
    @Schema(name = "상점ID")
    private String partnerId;

    @Column(name = "PARTNER_NAME", nullable = false)
    @Schema(name = "상점이름")
    private String partnerName;

    @JsonIgnore
    @OneToMany(mappedBy = "shop")
    private List<MemberShip> memberShip;
}

