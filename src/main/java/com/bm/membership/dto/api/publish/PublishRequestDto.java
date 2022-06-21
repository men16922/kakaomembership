package com.bm.membership.dto.api.publish;

import com.bm.membership.dto.request.ApiRequest;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import javax.validation.constraints.*;

/**
 * packageName    : com.bm.membership.dto.api.publish
 * fileName       : PublishRequestDto
 * author         : men16
 * date           : 2022-06-21
 * description    : 통합 바코드 발급 요청 Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21        men16       최초 생성
 */
@Getter
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PublishRequestDto extends ApiRequest {


    @NotNull(message = "user_id is Mandatory") @Required
    @Max(9) @Min(9)
    @Schema(description = "사용자ID", defaultValue = "1")
    private Long userId;


    @NotBlank(message = "order_id is Mandatory") @Required
    @Size(max = 50)
    @Schema(description = "주문ID", defaultValue = "order1")
    private String orderId;
}

