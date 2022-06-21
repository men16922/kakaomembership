package com.bm.membership.service.impl;

import com.bm.membership.dto.request.ApiRequest;
import com.bm.membership.dto.response.ApiResponse;
import com.bm.membership.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.bm.membership.service
 * fileName       : PublishService
 * author         : men16
 * date           : 2022-06-21
 * description    : 통합 바코드 발급 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21        men16       최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PublishService implements ApiService {

    @Override
    public ApiResponse executeApiService(ApiRequest apiRequest) {
        return null;
    }
}

