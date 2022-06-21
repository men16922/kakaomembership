package com.bm.membership.controller;

import com.bm.membership.dto.api.publish.PublishRequestDto;
import com.bm.membership.dto.response.ApiResponse;
import com.bm.membership.service.impl.PublishService;
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
 * fileName       : PublishController
 * author         : men16
 * date           : 2022-06-21
 * description    : 통합 바코드 발급 controller
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21        men16       최초 생성
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "통합 바코드 발급")
@RequestMapping("api/v1/membership")
public class PublishController {

    private final PublishService publishService;

    @PostMapping(path = "/publish")
    @Operation(summary = "통합 바코드 발급",
            description = "통합 바코드 발급")
    public ApiResponse publishApi(@Valid @RequestBody PublishRequestDto publishRequestDto) {
        return publishService.executeApiService(publishRequestDto);
    }
}

