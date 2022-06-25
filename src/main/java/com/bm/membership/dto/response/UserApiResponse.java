package com.bm.membership.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName    : com.bm.membership.dto.response
 * fileName       : ApiResponse
 * author         : men16
 * date           : 2022-06-20
 * description    : API 응답용 추상 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20        men16       최초 생성
 */
@ToString
public abstract class UserApiResponse {

    @Getter
    @Setter
    @JsonIgnore
    private transient String reasonKey;



    /**
     * @param resultCode
     */
    public abstract void setResultCode(String resultCode);


    /**
     * @param resultMessage
     */
    public abstract void setResultMessage(String resultMessage);


    /**
     * @return
     */
    public abstract String getResultCode();


    /**
     * @return
     */
    public abstract String getResultMessage();






}

