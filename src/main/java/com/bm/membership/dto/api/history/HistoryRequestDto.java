package com.bm.membership.dto.api.history;

import com.bm.membership.dto.request.ApiRequest;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * packageName    : com.bm.membership.dto.api.history
 * fileName       : HistoryRequestDto
 * author         : men16
 * date           : 2022-06-21
 * description    : 내역 조회 요청 Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21        men16       최초 생성
 */
@Schema(description = "사용내역 조회 request")
@Builder
@Setter
@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class HistoryRequestDto extends ApiRequest {

    @NotBlank(message = "membership_barcode is Mandatory") @Required
    @Size(min = 10, max = 10)
    @Schema(description = "멤버십바코드", defaultValue = "19KKSL3921")
    private String membershipBarcode;

    @NotBlank(message = "start_date is Mandatory") @Required
    @Size(max = 8)
    @Schema(description = "시작기간")
    private String startDate;

    @NotBlank(message = "end_date is Mandatory") @Required
    @Size(max = 8)
    @Schema(description = "종료기간")
    private String endDate;
}

