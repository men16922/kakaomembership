package com.bm.membership.dto.api.earn;

import com.bm.membership.dto.request.ApiRequest;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * packageName    : com.bm.membership.dto.api.earn
 * fileName       : EarnRequestDto
 * author         : men16
 * date           : 2022-06-21
 * description    : 포인트 적립 요청 Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21        men16       최초 생성
 */
@Schema(description = "포인트 적립 request")
@Setter
@Getter
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EarnRequestDto extends ApiRequest {

    @NotBlank(message = "partner_id is Mandatory") @Required
    @Size(max = 50)
    @Schema(description = "상점ID", defaultValue = "grocery1")
    private String partnerId;

    @NotBlank(message = "membership_barcode is Mandatory") @Required
    @Size(min = 10, max = 10)
    @Schema(description = "멤버십바코드", defaultValue = "ABCDEFGHIJ")
    private String membershipBarcode;

    @Positive
    @NotNull(message = "point is Mandatory") @Required
    @Schema(description = "적립금", defaultValue = "100")
    private Float point;

    @NotBlank(message = "order_id is Mandatory") @Required
    @Size(max = 50)
    @Schema(description = "주문ID", defaultValue = "order1")
    private String orderId;

}

