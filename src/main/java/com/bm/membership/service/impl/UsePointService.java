package com.bm.membership.service.impl;

import com.bm.membership.dto.request.ApiRequest;
import com.bm.membership.dto.response.ApiResponse;
import com.bm.membership.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.bm.membership.service.impl
 * fileName       : UsePointService
 * author         : men16
 * date           : 2022-06-21
 * description    : Point 사용 Service
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21        men16       최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UsePointService implements ApiService {

    @Override
    public ApiResponse executeApiService(ApiRequest apiRequest) {
        return null;
    }
}

