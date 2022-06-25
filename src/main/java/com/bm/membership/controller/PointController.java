package com.bm.membership.controller;

import com.bm.membership.aop.HttpLogging;
import com.bm.membership.dto.api.earn.EarnRequestDto;
import com.bm.membership.dto.api.earn.EarnResponseDto;
import com.bm.membership.dto.api.use.UseRequestDto;
import com.bm.membership.dto.api.use.UseResponseDto;
import com.bm.membership.dto.response.UserApiResponse;
import com.bm.membership.service.impl.EarnPointService;
import com.bm.membership.service.impl.UsePointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "포인트 적립/사용")
@RequestMapping("api/v1/membership/point")
public class PointController {

    private final UsePointService usePointService;

    private final EarnPointService earnPointService;

    @HttpLogging
    @PostMapping(path = "/earn")
    @Operation(summary = "포인트 적립",
            description = "포인트 적립")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "포인트 적립 성공", content = @Content(schema = @Schema(implementation = EarnResponseDto.class)))
    })
    public UserApiResponse earnApi(@Valid @RequestBody EarnRequestDto earnRequestDto) {
        log.info("EARN CONTROLLER PARAM -{}", earnRequestDto);
        return earnPointService.executeApiService(earnRequestDto);
    }

    @HttpLogging
    @PostMapping(path = "/use")
    @Operation(summary = "포인트 사용",
            description = "포인트 사용")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "포인트 사용 성공", content = @Content(schema = @Schema(implementation = UseResponseDto.class)))
    })
    public UserApiResponse useApi(@Valid @RequestBody UseRequestDto useRequestDto) {
        log.info("USE CONTROLLER PARAM -{}", useRequestDto);
        return usePointService.executeApiService(useRequestDto);
    }


}

