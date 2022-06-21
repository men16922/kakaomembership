package com.bm.membership.service;

import com.bm.membership.dto.request.ApiRequest;
import com.bm.membership.dto.response.ApiResponse;

/**
 * packageName    : com.bm.membership.service
 * fileName       : ApiService
 * author         : men16
 * date           : 2022-06-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21        men16       최초 생성
 */
public interface ApiService {

    public ApiResponse executeApiService(ApiRequest apiRequest);
}
