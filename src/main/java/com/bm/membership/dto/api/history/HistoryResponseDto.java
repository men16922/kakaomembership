package com.bm.membership.dto.api.history;

import com.bm.membership.dto.api.common.History;
import com.bm.membership.dto.response.UserApiResponse;
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
import java.util.List;

/**
 * packageName    : com.bm.membership.dto.api.history
 * fileName       : HistoryResponseDto
 * author         : men16
 * date           : 2022-06-21
 * description    : 내역 조회 응답 Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21        men16       최초 생성
 */
@Schema(description = "사용내역 조회 response")
@ToString
@Setter
@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class HistoryResponseDto extends UserApiResponse {

    @NotBlank
    @Size(max = 5) @Required
    @Schema(description = "응답코드")
    private String returncode;

    @NotBlank @Size(max = 255) @Required
    @Schema(description = "응답메시지")
    private String returnmsg;

    @Schema(description = "사용내역")
    private List<History> history;


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

