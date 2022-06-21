package com.bm.membership.dto.request;

import lombok.NoArgsConstructor;

/**
 * packageName    : com.bm.membership.dto.request
 * fileName       : ApiRequest
 * author         : men16
 * date           : 2022-06-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21        men16       최초 생성
 */
@NoArgsConstructor
public abstract class ApiRequest {

    protected String createdTxId;

    /**
     * @return
     */
    public void setCreatedTxId(String txId) {
        this.createdTxId = txId;

    }

    /**
     * @return
     */
    public String createdTxId() {
        return createdTxId;

    }


}


