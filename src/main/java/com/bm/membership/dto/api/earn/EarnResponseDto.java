package com.bm.membership.dto.api.earn;

import com.bm.membership.dto.response.ApiResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * packageName    : com.bm.membership.dto.api.earn
 * fileName       : EarnResponseDto
 * author         : men16
 * date           : 2022-06-21
 * description    : 포인트 적립 응답 Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21        men16       최초 생성
 */
@ToString
@Setter
@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EarnResponseDto extends ApiResponse {

    @NotBlank @Size(max = 255) @Required
    @Schema(description = "응답코드")
    private String returncode;

    @NotBlank @Size(max = 255) @Required
    @Schema(description = "응답메시지")
    private String returnmsg;

    @Size(min = 10, max = 10)
    @Schema(description = "멤버십 바코드")
    private String membershipBarcode;

    @Schema(description = "적립금")
    private Float point;

    @Size(max = 50)
    @Schema(description = "거래ID")
    private String tid;

    @Size(max = 50)
    @Schema(description = "주문ID")
    private String orderId;

    @Size(max = 20)
    @Schema(description = "승인시간")
    private String approvedAt;


    @Override
    public void setResultCode(String returncode) {
        this.returncode = returncode;
    }

    @Override
    public void setResultMessage(String returnmsg) {
        this.returnmsg = returnmsg;
    }

    @JsonIgnore
    @Override
    public String getResultCode() {
        return returncode;
    }

    @JsonIgnore
    @Override
    public String getResultMessage() {
        return returnmsg;
    }
}

