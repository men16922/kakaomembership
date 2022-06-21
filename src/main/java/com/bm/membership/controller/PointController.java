package com.bm.membership.controller;

import com.bm.membership.dto.api.earn.EarnRequestDto;
import com.bm.membership.dto.api.use.UseRequestDto;
import com.bm.membership.dto.response.ApiResponse;
import com.bm.membership.service.impl.EarnPointService;
import com.bm.membership.service.impl.UsePointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * packageName    : com.bm.membership.controller
 * fileName       : PointController
 * author         : men16
 * date           : 2022-06-21
 * description    : 포인트 적립/사용 controller
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21        men16       최초 생성
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "포인트 적립/사용")
@RequestMapping("api/v1/membership/point")
public class PointController {

    private final UsePointService usePointService;

    private final EarnPointService earnPointService;

    @PostMapping(path = "/earn")
    @Operation(summary = "포인트 적립",
            description = "포인트 적립")
    public ApiResponse earnApi(@Valid @RequestBody EarnRequestDto earnRequestDto) {
        return earnPointService.executeApiService(earnRequestDto);
    }

    @PostMapping(path = "/use")
    @Operation(summary = "포인트 사용",
            description = "포인트 사용")
    public ApiResponse useApi(@Valid @RequestBody UseRequestDto useRequestDto) {
        return usePointService.executeApiService(useRequestDto);
    }


}

