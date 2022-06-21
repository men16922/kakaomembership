package com.bm.membership.dto.api.common;

import com.bm.membership.common.enums.Category;
import com.bm.membership.common.enums.UsageType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;

/**
 * packageName    : com.bm.membership.dto.api
 * fileName       : History
 * author         : men16
 * date           : 2022-06-21
 * description    : 사용내역 Object
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21        men16       최초 생성
 */
@Builder
@Getter
@Setter
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class History {

    @Size(max = 20)
    @Schema(description = "승인시간")
    private String approvedAt;

    @Size(max = 10)
    @Schema(description = "사용유형")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private UsageType type;

    @Size(max = 10)
    @Schema(description = "업종")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Category category;

    @Size(max = 50)
    @Schema(description = "상점이름")
    private String partnerName;


}

