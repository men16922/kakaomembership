package com.bm.membership.dto;

import com.bm.membership.dto.response.UserApiResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * packageName    : com.bm.membership.dto
 * fileName       : CommonResponse
 * author         : men16
 * date           : 2022-06-20
 * description    : 일반적인 실패 응답용 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20        men16       최초 생성
 */

@ToString
@Setter
@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CommonResponseUser extends UserApiResponse {

    @NotNull
    @Max(value = 5)
    @Required
    private String returncode;

    @NotNull @Max(value = 255)
    @Required
    private String returnmsg;


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

