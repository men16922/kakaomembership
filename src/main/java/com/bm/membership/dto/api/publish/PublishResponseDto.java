package com.bm.membership.dto.api.publish;

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
 * packageName    : com.bm.membership.dto.api.publish
 * fileName       : PublishResponseDto
 * author         : men16
 * date           : 2022-06-21
 * description    : 통합 바코드 발급 응답 Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21        men16       최초 생성
 */
@ToString
@Setter
@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PublishResponseDto extends ApiResponse {

    @NotBlank @Size(max = 5) @Required
    @Schema(description = "응답코드")
    private String returncode;

    @NotBlank @Size(max = 255) @Required
    @Schema(description = "응답메시지")
    private String returnmsg;

    @Size(min = 10, max = 10)
    @Schema(description = "멤버십 바코드")
    private String membershipBarcode;

    @Size(max = 50)
    @Schema(description = "거래ID")
    private String tid;

    @Size(max = 50)
    @Schema(description = "주문ID")
    private String orderId;

    @Size(max = 50)
    @Schema(description = "등록시간")
    private String registeredAt;


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

