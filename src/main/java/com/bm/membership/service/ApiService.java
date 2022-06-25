package com.bm.membership.service;

import com.bm.membership.dto.request.ApiRequest;
import com.bm.membership.dto.response.UserApiResponse;

/**
 * packageName    : com.bm.membership.service
 * fileName       : ApiService
 * author         : men16
 * date           : 2022-06-21
 * description    : API 서비스 추상 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21        men16       최초 생성
 */
public interface ApiService {

    public UserApiResponse executeApiService(ApiRequest apiRequest);
}
