package com.bm.membership.controller;

import com.bm.membership.aop.HttpLogging;
import com.bm.membership.dto.api.publish.PublishRequestDto;
import com.bm.membership.dto.api.publish.PublishResponseDto;
import com.bm.membership.dto.response.UserApiResponse;
import com.bm.membership.service.impl.PublishService;
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
 * fileName       : PublishController
 * author         : men16
 * date           : 2022-06-21
 * description    : 통합 바코드 발급 controller
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21        men16       최초 생성
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "통합 바코드 발급")
@RequestMapping("api/v1/membership")
public class PublishController {

    private final PublishService publishService;

    @HttpLogging
    @PostMapping(path = "/publish")
    @Operation(summary = "통합 바코드 발급",
            description = "통합 바코드 발급")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통합 바코드 발급 성공", content = @Content(schema = @Schema(implementation = PublishResponseDto.class)))
    })
    public UserApiResponse publishApi(@Valid @RequestBody PublishRequestDto publishRequestDto) {
        log.info("PUBLISH CONTROLLER PARAM -{}", publishRequestDto);
        return publishService.executeApiService(publishRequestDto);
    }
}

